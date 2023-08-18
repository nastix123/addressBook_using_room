package by.eapp.addressbookusingroomsettings.contacts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val ID: Int? = 0,
    val first_name: String,
    val second_name: String,
    val phoneNumber:String
)
