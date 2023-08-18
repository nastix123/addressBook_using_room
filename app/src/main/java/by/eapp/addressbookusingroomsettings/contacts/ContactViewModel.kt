package by.eapp.addressbookusingroomsettings.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(
    private val dao: ContactDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _state = MutableStateFlow(ContactState())

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
               /* val firstName = _state.value
                val secondName
                viewModelScope.launch {

                }*/
            }
            is ContactEvent.SetFirstName -> {
                _state.update { it.copy(
                    first_name = event.first_name
                )

                }
            }
            is ContactEvent.SetPhoneNumber -> TODO()
            is ContactEvent.SetSecondName -> TODO()
            ContactEvent.ShowDialog -> TODO()
            is ContactEvent.SortContacts -> TODO()
        }
    }
}