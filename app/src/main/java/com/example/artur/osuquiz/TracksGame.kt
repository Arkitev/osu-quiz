package com.example.artur.osuquiz

import android.graphics.Color
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tracks_game.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*

class TracksGame : AppCompatActivity() {

    val mp = MediaPlayer()
    var points: Double = 0.0
    var randomTrackId: Int = 0
    var trackArtist: String = "artist"
    var trackTitle = "title"
    var trackUrl = "link"
    lateinit var jsonObject: JSONObject
    lateinit var jsonArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks_game)

        val gameMode = intent.getStringExtra("GAME_MODE")
        textViewGameMode.text = gameMode

        setTrackId(gameMode)



        //łączenie z bazą i pobranie z niej danych
        val tracksBaseUrl = "http://95.160.241.159:8081/tracks/?format=json"
        val client = OkHttpClient()
        val request = Request.Builder().url(tracksBaseUrl).build()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body: String? = response.body()?.string()
                jsonArray = JSONArray(body)
                jsonObject = jsonArray.getJSONObject(randomTrackId)

                trackArtist = jsonObject.getString("artist")
                trackTitle = jsonObject.getString("title")
                trackUrl = jsonObject.getString("link")

                loadTrack(trackUrl)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
        //



        mp.setOnCompletionListener {
            btPlay.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
        }

        btPlay.setOnClickListener() {
            if(!mp.isPlaying) {
                mp.start()
                btPlay.setBackgroundResource(R.drawable.ic_pause_black_24dp)
            } else {
                mp.pause()
                btPlay.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
            }
        }

        btReplay.setOnClickListener() {
            mp.seekTo(0)
        }

        btCheck.setOnClickListener() {
            checkArtistAndTitle()
        }

        btUnhide.setOnClickListener() {
            txtArtist.setText(trackArtist)
            txtTitle.setText(trackTitle)
            txtArtist.setTextColor(Color.GRAY)
            txtTitle.setTextColor(Color.GRAY)
            txtArtist.setEnabled(false)
            txtTitle.setEnabled(false)
            btCheck.setEnabled(false)
            btUnhide.setEnabled(false)
        }

        btNext.setOnClickListener() {
            mp.stop()
            mp.reset()

            setTrackId(gameMode)
            jsonObject = jsonArray.getJSONObject(randomTrackId)
            trackArtist = jsonObject.getString("artist")
            trackTitle = jsonObject.getString("title")
            trackUrl = jsonObject.getString("link")

            loadTrack(trackUrl)
            btPlay.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp)
            txtArtist.setText("")
            txtTitle.setText("")
            txtArtist.setTextColor(Color.BLACK)
            txtTitle.setTextColor(Color.BLACK)
            txtArtist.setEnabled(true)
            txtTitle.setEnabled(true)
            btCheck.setEnabled(true)
            btUnhide.setEnabled(true)
        }
    }

    private fun loadTrack(trackUrl: String) {
        mp.setDataSource(trackUrl)
        mp.prepare()
    }

    private fun setTrackId(gameMode: String) {
        if(gameMode == "osu!") {
            randomTrackId = Random().nextInt((3-0)) + 0
        } else if (gameMode == "osu!mania") {
            randomTrackId = Random().nextInt((6-3)) + 3
        } else if (gameMode == "osu!taiko") {
            randomTrackId = Random().nextInt((9-6)) + 6
        } else if (gameMode == "osu!catch") {
            randomTrackId = Random().nextInt((12-9)) + 9
        }
    }

    private fun checkArtistAndTitle() {
        if(txtArtist.getText().toString() == trackArtist) {
            txtArtist.setTextColor(Color.GREEN)
            points += 0.5

        } else
            txtArtist.setTextColor(Color.RED)

        if(txtTitle.getText().toString() == trackTitle) {
            txtTitle.setTextColor(Color.GREEN)
            points += 0.5
        } else
            txtTitle.setTextColor(Color.RED)

        txtArtist.setEnabled(false)
        txtTitle.setEnabled(false)
        btCheck.setEnabled(false)

        textViewPointsValue.text = points.toString()
    }
}



