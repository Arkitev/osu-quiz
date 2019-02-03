package com.example.artur.osuquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_backgrounds_game.*
import android.graphics.BitmapFactory
import java.net.URL


class BackgroundsGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backgrounds_game)

        textViewGameMode.text = intent.getStringExtra("GAME_MODE")

        val url = URL("https://b.ppy.sh/thumb/320118l.jpg")
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        imgBackground.setImageBitmap(bmp)

        btCheck.setOnClickListener() {

        }

        btUnhide.setOnClickListener() {

        }

        btNext.setOnClickListener() {

        }
    }
}
