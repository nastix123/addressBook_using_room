@file:OptIn(ExperimentalMaterial3Api::class)

package by.eapp.addressbookusingroomsettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.eapp.addressbookusingroomsettings.contacts.ContactEvent
import by.eapp.addressbookusingroomsettings.contacts.ContactState

@Composable
fun ScreenAddContact(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        title = { Text(text = "Add contact") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.first_name,
                    onValueChange = {
                        onEvent(ContactEvent.SetFirstName(it))
                    },
                    placeholder = {
                        Text(text = "First name")
                    }
                )
                TextField(
                    value = state.second_name,
                    onValueChange = {
                        onEvent(ContactEvent.SetSecondName(it))
                    },
                    placeholder = {
                        Text(text = "Second name")
                    }
                )
                TextField(
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(ContactEvent.SetPhoneNumber(it))
                    },
                    placeholder = {
                        Text(text = "Phone Number")
                    }
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ){
            Button(
                onClick = {
                    onEvent(ContactEvent.SaveContact)
                }
            ) {
                Text(text = "Add")
            }}
        },
        dismissButton = {

            Button(
                onClick = {

                }
            ) {
                Text(text = "Cancel")
            }
        }
    )
}
