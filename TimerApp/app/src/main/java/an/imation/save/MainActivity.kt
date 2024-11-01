package an.imation.save

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener{

    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    private var isTimerOn: Boolean = false
    private lateinit var button: Button
    private lateinit var countDownTimer: CountDownTimer
    private var defaultInterval: Int = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        seekBar.max = 600

        setIntervalFromSharedPreferences(sharedPreferences)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateTimer(progress * 1000L)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    private fun updateTimer(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000 / 60).toInt()
        val seconds = (millisUntilFinished / 1000 % 60).toInt()

        val minutesString = if (minutes < 10) "0$minutes" else minutes.toString()
        val secondsString = if (seconds < 10) "0$seconds" else seconds.toString()

        textView.text = "$minutesString:$secondsString"
    }

    fun start(view: View) {
        if (!isTimerOn) {
            button.text = "Stop"
            seekBar.isEnabled = false
            isTimerOn = true

            countDownTimer = object : CountDownTimer(seekBar.progress * 1000L, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    updateTimer(millisUntilFinished)
                }

                override fun onFinish() {
                    if (sharedPreferences.getBoolean("enable_sound", true)) {
                        playSound(sharedPreferences.getString("timer_melody", "bell"))
                    }
                    resetTimer()

                }
            }
            countDownTimer.start() // Запуск таймера
        } else {
            resetTimer()
        }
    }
    private fun playSound(melodyName: String?) {
      val soundResId = when (melodyName) {
          "bell" -> R.raw.bell_sound
         "alarm_siren" -> R.raw.alarm_siren_sound
         "bip" -> R.raw.bip_sound
         else -> return
     }
     MediaPlayer.create(applicationContext, soundResId).start()
     }
     private fun resetTimer() {
        countDownTimer.cancel()
         button.text = "Start"
        seekBar.isEnabled = true
         isTimerOn = false
        setIntervalFromSharedPreferences(sharedPreferences)
     }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "default_interval") {
            if (sharedPreferences != null) {
                setIntervalFromSharedPreferences(sharedPreferences)
            }
        }
    }

     override fun onCreateOptionsMenu(menu: Menu): Boolean {
         menuInflater.inflate(R.menu.timer_menu, menu)
         return true
    }
      override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
           }
           R.id.action_about -> {
               startActivity(Intent(this, AboutActivity::class.java))
                true
            }
             else -> super.onOptionsItemSelected(item)
         }
     }
    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
     }
    private fun setIntervalFromSharedPreferences(sharedPreferences: SharedPreferences) {
        val intervalString = sharedPreferences.getString("default_interval", "30") ?: "30"
        defaultInterval = intervalString.toIntOrNull() ?: run {
            Toast.makeText(this, "Invalid interval format, using default.", Toast.LENGTH_LONG).show()
            30 // Возвращаем 30, если преобразование не удалось
        }
        seekBar.progress = defaultInterval
        updateTimer(defaultInterval * 1000L)
    }

}





    //override fun onDestroy() {
    //    super.onDestroy()
    //    sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
   // }
