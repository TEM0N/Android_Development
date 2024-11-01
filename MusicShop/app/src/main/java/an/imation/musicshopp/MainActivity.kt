package an.imation.musicshopp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var quantity = 0
    private lateinit var spinner: Spinner
    private lateinit var spinnerArrayList: ArrayList<String>
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    private val goodsMap = HashMap<String, Double>()
    private lateinit var goodsName: String
    private var price: Double = 0.0
    private lateinit var userNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNameEditText = findViewById(R.id.nameEditText)

        createSpinner()
        createMap()
    }

    private fun createSpinner() {
        spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this
        spinnerArrayList = ArrayList()

        spinnerArrayList.add("guitar")
        spinnerArrayList.add("drums")
        spinnerArrayList.add("keyboard")

        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
    }

    private fun createMap() {
        goodsMap["guitar"] = 500.0
        goodsMap["drums"] = 1500.0
        goodsMap["keyboard"] = 1000.0
    }

    fun increaseQuantity(view: View) {
        quantity++
        updateQuantityAndPrice()
    }

    fun decreaseQuantity(view: View) {
        quantity = if (quantity > 0) quantity - 1 else 0
        updateQuantityAndPrice()
    }

    private fun updateQuantityAndPrice() {
        val quantityTextView: TextView = findViewById(R.id.quantityTextView)
        quantityTextView.text = quantity.toString()
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        priceTextView.text = (quantity * price).toString()
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        goodsName = spinner.selectedItem.toString()
        price = goodsMap[goodsName] ?: 0.0
        updateQuantityAndPrice()

        val goodsImageView: ImageView = findViewById(R.id.goodsImageView)

        when (goodsName) {
            "guitar" -> goodsImageView.setImageResource(R.drawable.guitar)
            "drums" -> goodsImageView.setImageResource(R.drawable.drums)
            "keyboard" -> goodsImageView.setImageResource(R.drawable.keyboard)
            else -> goodsImageView.setImageResource(R.drawable.guitar)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    fun addToCart(view: View) {
        val order = Order().apply {
            userName = userNameEditText.text.toString()
            this.goodsName = goodsName
            this.quantity = quantity
            this.price = price
            orderPrice = quantity * price
        }

        val orderIntent = Intent(this, OrderActivity::class.java).apply {
            putExtra("userNameForIntent", order.userName)
            putExtra("goodsName", order.goodsName)
            putExtra("quantity", order.quantity)
            putExtra("price", order.price)
            putExtra("orderPrice", order.orderPrice)
        }

        startActivity(orderIntent)
    }
}