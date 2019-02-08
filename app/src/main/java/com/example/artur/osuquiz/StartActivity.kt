package com.example.artur.osuquiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*
import android.content.Intent

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        btTrain.setOnClickListener() {
            val myIntent = Intent(this, GameModeActivity::class.java)
            startActivity(myIntent)
        }

        btOptions.setOnClickListener() {

        }

        btAbout.setOnClickListener() {
            val myIntent = Intent(this, AboutActivity::class.java)
            startActivity(myIntent)
        }

        btExit.setOnClickListener() {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }
    }
}
