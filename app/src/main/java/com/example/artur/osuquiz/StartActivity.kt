package com.example.artur.osuquiz

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log

private val TAG = "Permission"
private val REQUEST_CODE = 101

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

        }

        btExit.setOnClickListener() {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }
    }
}
