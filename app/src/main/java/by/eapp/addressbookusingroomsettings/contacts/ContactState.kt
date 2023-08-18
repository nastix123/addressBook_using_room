package by.eapp.addressbookusingroomsettings.contacts

data class ContactState(
    val contacts:List<Contact> = emptyList(),
    val first_name: String = "",
    val last_name: String = "",
    val phoneNumber: String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
