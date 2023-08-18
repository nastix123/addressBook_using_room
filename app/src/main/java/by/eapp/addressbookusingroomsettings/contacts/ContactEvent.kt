package by.eapp.addressbookusingroomsettings.contacts

sealed interface ContactEvent {


    object SaveContact: ContactEvent
    data class SetFirstName(val first_name: String):ContactEvent
    data class SetSecondName(val second_name: String):ContactEvent
    data class SetPhoneNumber(val phoneNumber: String):ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog:ContactEvent
    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact:Contact):ContactEvent

}