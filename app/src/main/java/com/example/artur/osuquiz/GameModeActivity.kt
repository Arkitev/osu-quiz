package com.example.artur.osuquiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game_mode.*

class GameModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_mode)

        btStandard.setOnClickListener() {
            val myIntent = Intent(this, WhatToTrain::class.java)
            myIntent.putExtra("GAME_MODE", "osu!")
            startActivity(myIntent)
        }

        btMania.setOnClickListener() {
            val myIntent = Intent(this, WhatToTrain::class.java)
            myIntent.putExtra("GAME_MODE", "osu!mania")
            startActivity(myIntent)
        }

        btTaiko.setOnClickListener() {
            val myIntent = Intent(this, WhatToTrain::class.java)
            myIntent.putExtra("GAME_MODE", "osu!taiko")
            startActivity(myIntent)
        }

        btCtb.setOnClickListener() {
            val myIntent = Intent(this, WhatToTrain::class.java)
            myIntent.putExtra("GAME_MODE", "osu!catch")
            startActivity(myIntent)
        }
    }
}
