package com.example.artur.osuquiz

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_backgrounds_game.*
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*

class BackgroundsGame : AppCompatActivity() {

    var points: Double = 0.0
    var randomBackgroundId: Int = 0
    var backgroundArtist: String = "artist"
    var backgroundTitle = "title"
    var backgroundUrl = "link"
    lateinit var jsonObject: JSONObject
    lateinit var jsonArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backgrounds_game)

        val gameMode = intent.getStringExtra("GAME_MODE")
        textViewGameMode.text = gameMode

        setBackgroundId(gameMode)



        //łączenie z bazą i pobranie z niej danych
        val tracksBaseUrl = "http://95.160.241.159:8081/backgrounds/?format=json"
        val client = OkHttpClient()
        val request = Request.Builder().url(tracksBaseUrl).build()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body: String? = response.body()?.string()
                jsonArray = JSONArray(body)
                jsonObject = jsonArray.getJSONObject(randomBackgroundId)

                backgroundArtist = jsonObject.getString("artist")
                backgroundTitle = jsonObject.getString("title")
                backgroundUrl = jsonObject.getString("link")

                runOnUiThread {
                    loadBackground(backgroundUrl)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
        //



        btCheck.setOnClickListener() {
            checkArtistAndTitle()
        }

        btUnhide.setOnClickListener() {
            txtArtist.setText(backgroundArtist)
            txtTitle.setText(backgroundTitle)
            txtArtist.setTextColor(Color.GRAY)
            txtTitle.setTextColor(Color.GRAY)
            txtArtist.setEnabled(false)
            txtTitle.setEnabled(false)
            btCheck.setEnabled(false)
            btUnhide.setEnabled(false)
        }

        btNext.setOnClickListener() {
            setBackgroundId(gameMode)
            jsonObject = jsonArray.getJSONObject(randomBackgroundId)
            backgroundArtist = jsonObject.getString("artist")
            backgroundTitle = jsonObject.getString("title")
            backgroundUrl = jsonObject.getString("link")

            txtArtist.setText("")
            txtTitle.setText("")
            txtArtist.setTextColor(Color.BLACK)
            txtTitle.setTextColor(Color.BLACK)
            txtArtist.setEnabled(true)
            txtTitle.setEnabled(true)
            btCheck.setEnabled(true)
            btUnhide.setEnabled(true)

            runOnUiThread {
                loadBackground(backgroundUrl)
            }
        }
    }

    private fun loadBackground(backgroundUrl: String) {
        Picasso.with(this).load(backgroundUrl).into(imgBackground)
    }

    private fun setBackgroundId(gameMode: String) {
        if(gameMode == "osu!") {
            randomBackgroundId = Random().nextInt((3-0)) + 0
        } else if (gameMode == "osu!mania") {
            randomBackgroundId = Random().nextInt((6-3)) + 3
        } else if (gameMode == "osu!taiko") {
            randomBackgroundId = Random().nextInt((9-6)) + 6
        } else if (gameMode == "osu!catch") {
            randomBackgroundId = Random().nextInt((12-9)) + 9
        }
    }

    private fun checkArtistAndTitle() {
        if(txtArtist.getText().toString() == backgroundArtist) {
            txtArtist.setTextColor(Color.GREEN)
            points += 0.5

        } else
            txtArtist.setTextColor(Color.RED)

        if(txtTitle.getText().toString() == backgroundTitle) {
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
