package com.example.videotrimmer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import org.florescu.android.rangeseekbar.RangeSeekBar
import org.florescu.android.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener
import java.io.File

class TrimActivity : AppCompatActivity() {
    var uri: Uri? = null
    var imageView: ImageView? = null
    var videoView: VideoView? = null
    var textViewLeft: TextView? = null
    var textViewRight: TextView? = null
    var isPlaying: Boolean = false
    var duration = 0
    var filePrefix = ""
    var command: Array<String>? = null
    val dest: File? = null
    val originalPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trim)
        imageView = findViewById(R.id.pause)
        videoView = findViewById(R.id.videoView)
        textViewLeft = findViewById(R.id.tvLeft)
        textViewRight = findViewById(R.id.tvRight)
        var rangeSeekBar = findViewById<RangeSeekBar<Int>>(R.id.seekBar)

        val intent = intent
        if (intent != null) {
            val imgPath = intent.getStringExtra("uri")
            uri = Uri.parse(imgPath)
            isPlaying = true
            videoView?.setVideoURI(uri)
            videoView?.start()
        }
        setListeners(rangeSeekBar)
    }

    private fun setListeners(rangeSeekBar: RangeSeekBar<Int>) {
        imageView?.setOnClickListener {
            if (isPlaying) {
                imageView?.setImageResource(R.drawable.ic_play)
                videoView?.pause()
                isPlaying = false
            } else {
                videoView?.start()
                isPlaying = true
                imageView?.setImageResource(R.drawable.ic_pause)
            }
        }
        videoView?.setOnPreparedListener {
            videoView?.start()
            duration = it.duration / 1000
            textViewLeft?.text = "00:00:00"
            textViewRight?.text = getTime(it.duration / 1000)
            it.isLooping = true
            rangeSeekBar.setRangeValues(0, duration)
            rangeSeekBar.selectedMaxValue = duration
            rangeSeekBar.selectedMinValue = 0
            rangeSeekBar.isEnabled = true
            rangeSeekBar.setOnRangeSeekBarChangeListener { bar, minValue, maxValue ->

            }
        }
    }
}