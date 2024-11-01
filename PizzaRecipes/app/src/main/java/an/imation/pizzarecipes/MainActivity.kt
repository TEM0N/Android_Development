package an.imation.pizzarecipes

import an.imation.pizzarecipes.R.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val pizzaRecipeItems = arrayListOf<PizzaRecipesItem>()
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_1, Utils.PIZZA_1_TITLE, Utils.PIZZA_1_DESCRIPTION,
            Utils.PIZZA_1_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_2, Utils.PIZZA_2_TITLE, Utils.PIZZA_2_DESCRIPTION,
            Utils.PIZZA_2_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_3, Utils.PIZZA_3_TITLE, Utils.PIZZA_3_DESCRIPTION,
            Utils.PIZZA_3_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_4, Utils.PIZZA_4_TITLE, Utils.PIZZA_4_DESCRIPTION,
            Utils.PIZZA_4_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_5, Utils.PIZZA_5_TITLE, Utils.PIZZA_5_DESCRIPTION,
            Utils.PIZZA_5_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_6, Utils.PIZZA_6_TITLE, Utils.PIZZA_6_DESCRIPTION,
            Utils.PIZZA_6_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_7, Utils.PIZZA_7_TITLE, Utils.PIZZA_7_DESCRIPTION,
            Utils.PIZZA_7_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_8, Utils.PIZZA_8_TITLE, Utils.PIZZA_8_DESCRIPTION,
            Utils.PIZZA_8_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_9, Utils.PIZZA_9_TITLE, Utils.PIZZA_9_DESCRIPTION,
            Utils.PIZZA_9_RECIPE))
        pizzaRecipeItems.add(PizzaRecipesItem(drawable.pizza_10,Utils.PIZZA_10_TITLE, Utils.PIZZA_10_DESCRIPTION,
            Utils.PIZZA_10_RECIPE))

        recyclerView = findViewById(id.recyclerView)
        recyclerView.setHasFixedSize(true)

        adapter = PizzaRecipeAdapter(pizzaRecipeItems = pizzaRecipeItems, context = this)
        layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager



    }
}