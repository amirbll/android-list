package com.example.myapplication.feature.detail

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.response.University
import com.example.myapplication.data.remote.Api
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private val api = Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listViewUniversities)

        api.universities().enqueue(object : retrofit2.Callback<List<University>> {
            override fun onFailure(call: Call<List<University>>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<University>>, response: Response<List<University>>) {
                val universitiesList = response.body()?.map { it.name }!!.toList()
                listView.adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, universitiesList)
                Toast.makeText(applicationContext, "Data Received", Toast.LENGTH_SHORT).show()
            }
        })
    }

}