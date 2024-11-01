package an.imation.PaintApp

import an.imation.tink.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    lateinit var view: DrawPath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.drawPath)

        findViewById<AppCompatButton>(R.id.undo).setOnClickListener {
            view.clearLines()
        }
    }
}
