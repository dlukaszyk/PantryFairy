package com.myapp.pantryfairy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.myapp.pantryfairy.R
import com.myapp.pantryfairy.model.PantryItem
import com.myapp.pantryfairy.model.Recipe
import com.myapp.pantryfairy.model.matchRecipes

@Composable
fun CookNowScreen(
//    recipes: List<Recipe>,
//    pantry: List<PantryItem>
) {
//    val matches = matchRecipes(recipes, pantry)
//
//    Column(modifier = Modifier.padding(16.dp)) {
//
//        Text(stringResource(R.string.cook_now))
//        Spacer(Modifier.height(16.dp))
//        matches.forEach {
//            Text(it.recipe.name)
//            if (it.canCook) {
//                Text(stringResource(R.string.can_cook))
//            } else {
//                Text(stringResource(R.string.missing))
//                it.missing.forEach { item ->
//                    Text("• $item")
//                }
//            }
//            Spacer(Modifier.height(12.dp))
//        }
//    }
}