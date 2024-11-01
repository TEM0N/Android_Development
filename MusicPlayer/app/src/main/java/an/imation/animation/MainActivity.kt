package an.imation.animation

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
        private lateinit var mediaPlayer: MediaPlayer
        private lateinit var playPauseIcon: ImageView
        private lateinit var seekBar:SeekBar
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            playPauseIcon = findViewById(R.id.playIconImageView)
            mediaPlayer = MediaPlayer.create(this, R.raw.stuff)
            seekBar = findViewById(R.id.seekBar)
            seekBar.max = mediaPlayer.duration
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            val timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        seekBar.progress = mediaPlayer.currentPosition
                    }
                }
            }, 0, 1000)
            //mediaPlayer.start()
        }

    fun next(view: View) {
        seekBar.progress = mediaPlayer.duration
        mediaPlayer.seekTo(mediaPlayer.duration)
        mediaPlayer.pause()
        playPauseIcon.setImageResource(R.drawable.baseline_play_arrow_orange_24)}
    fun previous(view: View) {
        seekBar.progress = 0
        mediaPlayer.seekTo(0)
        mediaPlayer.pause()
        playPauseIcon.setImageResource(R.drawable.baseline_play_arrow_orange_24)}
    fun play(view: View) {
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            playPauseIcon.setImageResource(R.drawable.baseline_play_arrow_orange_24)
        }
        else {
            mediaPlayer.start()
            playPauseIcon.setImageResource(R.drawable.baseline_pause_pause_24)
        }
    }
}

