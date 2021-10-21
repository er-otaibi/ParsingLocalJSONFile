package com.example.parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var rvMain: RecyclerView
    var photos = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        rvMain = findViewById(R.id.rvMain)

        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonarr = JSONArray(json)

            for (i in 0 until jsonarr.length()) {
                var jsonobj = jsonarr.getJSONObject(i)

                photos.add(jsonobj.getString("url"))
            }
            rvMain.adapter = PicAdapter(photos)
            rvMain.layoutManager = LinearLayoutManager(this)

        } catch (e: IOException) {

        }

    }
}


