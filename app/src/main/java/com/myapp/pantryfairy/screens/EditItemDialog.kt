package com.myapp.pantryfairy.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.myapp.pantryfairy.R
import com.myapp.pantryfairy.data.entity.PantryItemEntity


@Composable
fun EditItemDialog(
    item: PantryItemEntity,
    onSave: (PantryItemEntity) -> Unit,
    onDismiss: () -> Unit
) {

    var name by remember {
        mutableStateOf(item.name)
    }

    var quantity by remember {
        mutableStateOf(
            item.quantity?.toString() ?: ""
        )
    }


    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text(stringResource(R.string.edit_item))
        },

        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(stringResource(R.string.name))
                    }
                )


                OutlinedTextField(
                    value = quantity,
                    onValueChange = {
                        quantity = it
                    },
                    label = {
                        Text(stringResource(R.string.quantity))
                    }
                )
            }
        },


        confirmButton = {

            Button(
                onClick = {

                    onSave(
                        item.copy(
                            name = name,
                            quantity = quantity.toDoubleOrNull()
                        )
                    )
                }
            ) {
                Text(stringResource(R.string.save))
            }
        },


        dismissButton = {

            Button(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}