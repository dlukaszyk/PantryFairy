package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.myapp.pantryfairy.data.database.AppDatabase
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.model.PantryItem
import com.myapp.pantryfairy.ui.viewmodel.PantryViewModel
import com.myapp.pantryfairy.ui.viewmodel.PantryViewModelFactory

@Composable
fun PantryScreen() {
    val context = LocalContext.current

    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pantry_db"
        ).build()
    }

    val repository = remember {
        PantryRepository(db.pantryDao())
    }

    val viewModel: PantryViewModel = viewModel(
        factory = PantryViewModelFactory(repository)
    )

    val items by viewModel.pantryItems.collectAsState()

    val pantry = remember { mutableStateListOf<PantryItem>() }
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Pantry")

        Spacer(Modifier.height(16.dp))

        pantry.forEach { item ->

            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    checked = item.has,
                    onCheckedChange = {
                        val index = pantry.indexOf(item)
                        pantry[index] = item.copy(has = it)
                    }
                )

                Text(item.name)
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = { showDialog = true }) {
            Text("+ Add item")
        }
    }

    if (showDialog) {
        AddItemDialog(
            onAdd = { name ->
                pantry.add(PantryItem(name))
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun AddItemDialog(
    onAdd: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add ingredient") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("e.g. eggs") }
            )
        },
        confirmButton = {
            Button(onClick = {
                if (text.isNotBlank()) onAdd(text.trim())
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}