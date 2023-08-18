package by.eapp.addressbookusingroomsettings.contacts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {

    @Upsert
    suspend fun createContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT *FROM contact ORDER BY first_name ASC")
    fun getContactOrderedByFirstName(): Flow<List<Contact>>

    @Query("SELECT *FROM contact ORDER BY second_name ASC")
    fun getContactOrderedByLastName(): Flow<List<Contact>>

    @Query("SELECT *FROM contact ORDER BY phoneNumber ASC")
    fun getContactOrderedByPhoneNumber(): Flow<List<Contact>>
}