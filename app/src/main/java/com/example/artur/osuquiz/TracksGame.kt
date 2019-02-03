package com.example.artur.osuquiz

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tracks_game.*

class TracksGame : AppCompatActivity() {

    val mp = MediaPlayer()
    var isTrackPlaying: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks_game)

        textViewGameMode.text = intent.getStringExtra("GAME_MODE")
        val trackUrl = "//b.ppy.sh/preview/320118.mp3"

        setTrack(trackUrl)

        btPlay.setOnClickListener() {
            if(!isTrackPlaying) {
                mp.prepare()
                mp.start()
                isTrackPlaying = true
                btPlay.setBackgroundResource(R.drawable.ic_pause_black_24dp)
            } else {
                mp.pause()
                isTrackPlaying = false
                btPlay.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
            }
        }

        btReplay.setOnClickListener() {

        }

        btCheck.setOnClickListener() {

        }

        btUnhide.setOnClickListener() {

        }

        btNext.setOnClickListener() {

        }
    }

    private fun setTrack(trackUrl: String) {
        mp.setDataSource(trackUrl)
    }
}
