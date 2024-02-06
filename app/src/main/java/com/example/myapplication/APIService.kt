package com.example.myapplication

import com.example.myapplication.model.FetchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("hiring.json")
    fun getItems(): Call<List<FetchResponse>>
}