package com.example.myapplication.data.remote

import com.example.myapplication.data.response.University
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("getUniversities")
    fun universities(): Call<List<University>>

    companion object {
        const val BASE_URL = "http://localhost/api/"
    }
}