package an.imation.musicshopp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class OrderActivity : AppCompatActivity() {
    private val addresses = arrayOf("pipko2004@mail.ru")
    private val subject = "Order from Music Shop"
    private var emailText: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        title = "Your order"
        val receivedOrderIntent = intent
        val userName = receivedOrderIntent.getStringExtra("userNameForIntent")
        val goodsName = receivedOrderIntent.getStringExtra("goodsName")
        val quantity = receivedOrderIntent.getIntExtra("quantity", 0)
        val price = receivedOrderIntent.getDoubleExtra("price", 0.0)
        val orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0.0)

        emailText = "Customer name: $userName\n" +
                "Goods name: $goodsName\n" +
                "Quantity: $quantity\n" +
                "Price: $price\n" +
                "Order Price: $orderPrice"

        val orderTextView = findViewById<TextView>(R.id.orderTextView)
        orderTextView.text = emailText
    }

    fun submitOrder(view: View) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_STREAM, emailText)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}