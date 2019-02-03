package com.example.artur.osuquiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_what_to_train.*

class WhatToTrain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_what_to_train)

        val gameMode = intent.getStringExtra("GAME_MODE")

        btTracks.setOnClickListener() {
            val myIntent = Intent(this, TracksGame::class.java)
            myIntent.putExtra("GAME_MODE", gameMode)
            startActivity(myIntent)
        }

        btBackgrounds.setOnClickListener() {
            val myIntent = Intent(this, BackgroundsGame::class.java)
            myIntent.putExtra("GAME_MODE", gameMode)
            startActivity(myIntent)
        }
    }
}
