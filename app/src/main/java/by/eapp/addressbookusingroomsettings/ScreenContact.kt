@file:OptIn(ExperimentalMaterial3Api::class)

package by.eapp.addressbookusingroomsettings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.eapp.addressbookusingroomsettings.contacts.ContactEvent
import by.eapp.addressbookusingroomsettings.contacts.ContactState
import by.eapp.addressbookusingroomsettings.contacts.SortType

@Composable
fun ScreenContact(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit
) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ContactEvent.ShowDialog) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact")
            }
        }
            ) {     padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
            SortType.values().forEach { sortType ->
            Row (
                modifier = Modifier
                    .clickable {
                        onEvent(ContactEvent.SortContacts(sortType))
                    }
            ) {

        }
}
                }
            }
        }
        
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewScreenContact() {
    ScreenContact(state = , onEvent = )
}*/
