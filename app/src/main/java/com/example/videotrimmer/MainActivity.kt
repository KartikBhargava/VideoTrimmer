package com.example.videotrimmer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var selectedUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.pickVideoBtn)
        btn.setOnClickListener {
          openVideo()
        }
    }

    fun openVideo() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.apply {
            type = "video/*"
            startActivityForResult(this, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            selectedUri = data?.data
            val intent = Intent(this, TrimActivity::class.java)
            intent.putExtra("uri", selectedUri.toString())
            startActivity(intent)
        }
    }
}