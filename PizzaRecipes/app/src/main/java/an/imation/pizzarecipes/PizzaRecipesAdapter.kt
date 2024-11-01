package an.imation.pizzarecipes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class PizzaRecipeAdapter(
     val pizzaRecipeItems: ArrayList<PizzaRecipesItem>,
    private val context: Context
) : RecyclerView.Adapter<PizzaRecipeAdapter.PizzaRecipesViewHolder>() {

    class PizzaRecipesViewHolder(
        itemView: View,
        private val context: Context,
        private val pizzaRecipeItems: ArrayList<PizzaRecipesItem>
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.pizzaImageView)
        val textView1: TextView = itemView.findViewById(R.id.titleTextView)
        val textView2: TextView = itemView.findViewById(R.id.descriptionTextView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val clickedPizzaRecipeItem = pizzaRecipeItems[position]

            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("imageResource", clickedPizzaRecipeItem.imageResource)
            intent.putExtra("title", clickedPizzaRecipeItem.text1)
            intent.putExtra("text2", clickedPizzaRecipeItem.text2)
            intent.putExtra("recipe", clickedPizzaRecipeItem.recipe)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PizzaRecipesViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.pizza_recipes_item, viewGroup, false)
        return PizzaRecipesViewHolder(view, context, pizzaRecipeItems)
    }

    override fun onBindViewHolder(recyclerViewViewHolder: PizzaRecipesViewHolder, i: Int) {
        val pizzaRecipeItem = pizzaRecipeItems[i]
        recyclerViewViewHolder.imageView.setImageResource(pizzaRecipeItem.imageResource)
        recyclerViewViewHolder.textView1.text = pizzaRecipeItem.text1
        recyclerViewViewHolder.textView2.text = pizzaRecipeItem.text2
    }

    override fun getItemCount(): Int {
        return pizzaRecipeItems.size
    }
}