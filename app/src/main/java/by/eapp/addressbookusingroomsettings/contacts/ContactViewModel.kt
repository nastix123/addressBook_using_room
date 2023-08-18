package by.eapp.addressbookusingroomsettings.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(
    private val dao: ContactDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.FIRST_NAME -> dao.getContactOrderedByFirstName()
                SortType.LAST_NAME -> dao.getContactOrderedByLastName()
                SortType.PHONE_NUMBER -> dao.getContactOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(ContactState())
    val state = combine(_state, _sortType, _contacts) { currentState, sortType, contacts ->
        currentState.copy(
            contacts = contacts,
            sortType = sortType

        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactEvent) {
        when (event){
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(contact = event.contact)
                }
            }
            ContactEvent.HideDialog -> {
            _state.update {
                it.copy(
                    isAddingContact = false
                )
            }
            }
            ContactEvent.SaveContact -> {
                val firstName = state.value.first_name
                val secondName = state.value.second_name
                val phoneNumber = state.value.phoneNumber

                if (firstName.isBlank() || secondName.isBlank() || phoneNumber.isBlank()) {
                    return
                }
                val contact = Contact(
                    first_name = firstName,
                    second_name = secondName,
                    phoneNumber = phoneNumber
                )

                viewModelScope.launch {
                    dao.createContact(contact)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    first_name = "",
                    second_name = "",
                    phoneNumber = ""

                )

                }
            }
            is ContactEvent.SetFirstName -> {
                _state.update { it.copy(
                    first_name = event.first_name
                )
                }
            }
            is ContactEvent.SetPhoneNumber -> {
                _state.update { it.copy(
                    phoneNumber = event.phoneNumber
                )
                }
            }
            is ContactEvent.SetSecondName -> {
                _state.update { it.copy(
                    second_name = event.second_name
                )
                }
            }
            ContactEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                )
                }
            }
            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }
        }
    }
}