package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.pantryfairy.model.PantryItem
import com.myapp.pantryfairy.model.Recipe
import com.myapp.pantryfairy.model.matchRecipes

@Composable
fun HomeScreen(
    recipes: List<Recipe>,
    pantry: List<PantryItem>
) {
    val matches = matchRecipes(recipes, pantry)
    val canCook = matches.filter { it.canCook }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Home")
        Spacer(Modifier.height(16.dp))

        Text("You can cook now:")
        canCook.forEach {
            Text("✔ ${it.recipe.name}")
        }
    }
}