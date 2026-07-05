package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.pantryfairy.model.PantryItem

@Composable
fun PantryScreen() {

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