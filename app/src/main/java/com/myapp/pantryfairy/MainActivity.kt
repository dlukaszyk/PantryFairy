package com.myapp.pantryfairy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.myapp.pantryfairy.data.database.DatabaseProvider
import com.myapp.pantryfairy.data.repository.PantryRepository
import com.myapp.pantryfairy.data.repository.RecipeRepository
import com.myapp.pantryfairy.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(this)

        val pantryRepository = PantryRepository(
            database.pantryDao()
        )

        val recipeRepository = RecipeRepository(
            database.recipeDao(),
            database.recipeIngredientDao()
        )

        setContent {
            MaterialTheme {
                AppNavigation(
                    pantryRepository = pantryRepository,
                    recipeRepository = recipeRepository
                )
            }
        }
    }
}


/*
Wyobraź sobie, że aplikacja to restauracja:

Screen = kelner, który rozmawia z klientem
ViewModel = kierownik sali, który wie, co trzeba zrobić
Repository = osoba, która wie skąd wziąć składniki
DAO = magazynier, który faktycznie umie wyjąć rzeczy z magazynu
Room Database = sam magazyn
Entity = produkt zapisany w magazynie

 Entity — opisuje jak wygląda rekord w bazie
 DAO — mówi Roomowi jakie operacje wykonać
 Database — tworzy połączenie z bazą
 Repository — ukrywa DAO przed resztą aplikacji
 */

