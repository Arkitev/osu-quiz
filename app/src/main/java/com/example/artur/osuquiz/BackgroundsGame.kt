package com.example.artur.osuquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_backgrounds_game.*
import com.squareup.picasso.Picasso

class BackgroundsGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backgrounds_game)

        textViewGameMode.text = intent.getStringExtra("GAME_MODE")
        val backgroundUrl = "https://b.ppy.sh/thumb/320118l.jpg"

        loadImage(backgroundUrl)

        btCheck.setOnClickListener() {

        }

        btUnhide.setOnClickListener() {

        }

        btNext.setOnClickListener() {

        }
    }

    private fun loadImage(backgroundUrl: String) {
        Picasso.with(this).load(backgroundUrl).into(imgBackground)
    }
}
