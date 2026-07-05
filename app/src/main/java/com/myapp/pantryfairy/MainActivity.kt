package com.myapp.pantryfairy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.myapp.pantryfairy.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("APP OPENED")
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PantryApp()
        }
    }
}
data class PantryItem(
    val name: String,
    val has: Boolean = false,
    val quantity: Double? = null
)

@Composable
fun PantryApp() {
    PantryHomeScreen()
}

@Composable
fun PantryHomeScreen() {

    val pantry = remember {
        mutableStateListOf(
            PantryItem("eggs", true, 6.0),
            PantryItem("milk", true),
            PantryItem("flour", false)
        )
    }

    val recipes = listOf(
        Recipe("Omelette", listOf("eggs", "milk")),
        Recipe("Pancakes", listOf("eggs", "milk", "flour")),
        Recipe("Scrambled Eggs", listOf("eggs")),
        Recipe("Pasta", listOf("pasta", "tomato"))
    )

    val matches = matchRecipes(recipes, pantry)

    val canCook = matches.filter { it.canCook }
    val almost = matches.filter { !it.canCook }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("PantryPal")

        Spacer(Modifier.height(16.dp))

        Text("You can cook")

        canCook.forEach {
            Text("✔ ${it.recipe.name}")
        }

        Spacer(Modifier.height(16.dp))

        Text("Almost there")

        almost.forEach {
            Text("~ ${it.recipe.name}")

            Text("Missing:")
            it.missing.forEach { item ->
                Text("• $item")
            }
        }
    }
}

@Composable
fun PantryScreen() {

    val pantry = remember {
        mutableStateListOf(
            PantryItem("eggs", true, 6.0),
            PantryItem("milk", true),
            PantryItem("flour", false)
        )
    }

    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("PantryPal")

        Spacer(Modifier.height(16.dp))

        Text("Pantry")

        pantry.forEach { item ->

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
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
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("e.g. eggs") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (text.isNotBlank()) onAdd(text.trim())
                }
            ) {
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

data class Recipe(
    val name: String,
    val ingredients: List<String>
)

fun extractPantry(recipes: List<Recipe>): Set<String> {
    return recipes.flatMap { it.ingredients }.toSet()
}

val pantryState = mapOf(
    "eggs" to true,
    "milk" to true,
    "flour" to false
)

val recipes = listOf(
    Recipe("Omelette", listOf("eggs", "milk")),
    Recipe("Pancakes", listOf("eggs", "milk", "flour")),
    Recipe("Scrambled Eggs", listOf("eggs")),
    Recipe("Pasta", listOf("pasta", "tomato"))
)

data class RecipeMatch(
    val recipe: Recipe,
    val missing: List<String>
) {
    val canCook: Boolean
        get() = missing.isEmpty()
}

fun matchRecipes(
    recipes: List<Recipe>,
    pantry: SnapshotStateList<PantryItem>
): List<RecipeMatch> {
    return recipes.map { recipe ->
        val missing = recipe.ingredients.filter {
            pantryState[it] != true
        }
        RecipeMatch(
            recipe = recipe,
            missing = missing
        )
    }
}

*/