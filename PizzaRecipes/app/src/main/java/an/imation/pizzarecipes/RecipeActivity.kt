package an.imation.pizzarecipes

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import an.imation.pizzarecipes.databinding.ActivityRecipeBinding
import android.widget.TextView

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val recipeTextView: TextView = findViewById(R.id.recipeTextView)

        val intent = intent
        if (intent != null) {
            titleTextView.text = intent.getStringExtra("title")
            recipeTextView.text = intent.getStringExtra("recipe")
        }
    }
}