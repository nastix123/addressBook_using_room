package by.eapp.addressbookusingroomsettings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import by.eapp.addressbookusingroomsettings.contacts.ContactDatabase
import by.eapp.addressbookusingroomsettings.contacts.ContactViewModel
import by.eapp.addressbookusingroomsettings.ui.theme.AddressBookUsingRoomSettingsTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts.db"
        ).build()
    }

    private val viewModel by viewModels<ContactViewModel> {
        object : ViewModelProvider.Factory {
             override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ContactViewModel(db.dao) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AddressBookUsingRoomSettingsTheme {
                    val state by viewModel.state.collectAsState()

                    ScreenContact(
                        state = state,
                        onEvent = viewModel::onEvent)

                }
            }
            }

        }
    }


