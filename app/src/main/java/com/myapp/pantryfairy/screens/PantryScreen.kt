package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.pantryfairy.data.entity.PantryItemEntity
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.viewmodel.PantryViewModel
import com.myapp.pantryfairy.viewmodel.PantryViewModelFactory
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.stringResource
import com.myapp.pantryfairy.R

@Composable
fun PantryScreen(
    repository: PantryRepository
) {

    val factory = PantryViewModelFactory(repository)

    val viewModel: PantryViewModel = viewModel(
        factory = factory
    )

    val items by viewModel.pantryItems.collectAsState()

    val availableItems = items.filter { it.has }
    val missingItems = items.filter { !it.has }

    var showDialog by remember {
        mutableStateOf(false)
    }
    var editingItem by remember {
        mutableStateOf<PantryItemEntity?>(null)
    }

    val selectedItems = remember {
        mutableStateListOf<Long>()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Text(stringResource(R.string.pantry))

        Spacer(Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                Text(stringResource(R.string.available))
            }
            items(availableItems) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            editingItem = item
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = selectedItems.contains(item.id),
                        onCheckedChange = { checked ->

                            if (checked) {
                                selectedItems.add(item.id)
                            } else {
                                selectedItems.remove(item.id)
                            }
                        }
                    )

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(item.name)

                        item.quantity?.let {
                            Text(
                                text = formatQuantity(it),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            viewModel.deleteItem(item)
                            selectedItems.remove(item.id)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_item)
                        )
                    }
                }
            }


            item {
                Spacer(
                    Modifier.height(16.dp)
                )
                Text(stringResource(R.string.missing_title))
            }
            items(missingItems) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            editingItem = item
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = selectedItems.contains(item.id),
                        onCheckedChange = { checked ->

                            if (checked) {
                                selectedItems.add(item.id)
                            } else {
                                selectedItems.remove(item.id)
                            }
                        }
                    )

                    Text(
                        text = item.name,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            viewModel.deleteItem(item)
                            selectedItems.remove(item.id)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_item)
                        )
                    }
                }
            }
        }

        val selectedAvailable = items
            .filter { selectedItems.contains(it.id) && it.has }

        val selectedMissing = items
            .filter { selectedItems.contains(it.id) && !it.has }


        if (selectedMissing.isNotEmpty()) {

            Button(
                onClick = {

                    selectedMissing.forEach { item ->
                        viewModel.updateItem(
                            item.copy(has = true)
                        )
                    }

                    selectedItems.clear()
                }
            ) {
                Text(stringResource(R.string.add_to_pantry))
            }
        }


        if (selectedAvailable.isNotEmpty()) {

            Button(
                onClick = {
                    selectedAvailable.forEach { item ->
                        viewModel.updateItem(
                            item.copy(
                                has = false,
                                quantity = 0.0
                            )
                        )
                    }

                    selectedItems.clear()
                }
            ) {
                Text(stringResource(R.string.remove_from_pantry))
            }
        }

        Button(
            onClick = { showDialog = true }
        ) {
            Text(stringResource(R.string.add_item))
        }
    }
    if (showDialog) {
        AddItemDialog(
            onAdd = { name, quantity ->

                viewModel.addItem(
                    PantryItemEntity(
                        name = name,
                        quantity = quantity,
                        has = true
                    )
                )
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }

    if (editingItem != null) {

        EditItemDialog(
            item = editingItem!!,

            onSave = { updatedItem ->

                viewModel.updateItem(updatedItem)

                editingItem = null
            },

            onDismiss = {
                editingItem = null
            }
        )
    }
}

@Composable
fun AddItemDialog(
    onAdd: (String, Double?) -> Unit,
    onDismiss: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var quantityText by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_ingredient)) },
        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text(stringResource(R.string.placeholder_eggs)) }
                )

                OutlinedTextField(
                    value = quantityText,
                    onValueChange = { quantityText = it },
                    placeholder = { Text(stringResource(R.string.quantity)) }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (text.isNotBlank()) onAdd(
                    text.trim(),
                    quantityText.toDoubleOrNull()
                )
            }) {
                Text(stringResource(R.string.add))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

fun formatQuantity(quantity: Double): String {
    return if (quantity % 1.0 == 0.0) {
        quantity.toInt().toString()
    } else {
        quantity.toString()
    }
}