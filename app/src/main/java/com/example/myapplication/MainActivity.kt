package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.GroupedViewAdapter
import com.example.myapplication.Adapter.ViewAdapter
import com.example.myapplication.model.FetchResponse
import com.example.myapplication.model.GroupedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.getItems().enqueue(object : Callback<List<FetchResponse>> {
            override fun onResponse(call: Call<List<FetchResponse>>, response: Response<List<FetchResponse>>) {
                if (response.isSuccessful) {
                    val items = response.body()?.map { FetchResponse(it.id, it.name, it.listId) } ?: emptyList()
                    val filteredItems = items.filter { it.name?.isNotBlank() == true }
                    val groupedItems = filteredItems.groupBy { it.listId }
                    val sortedGroupedItems = groupedItems.map { (listId, items) ->
                        GroupedItem(listId, items.sortedBy { it.name })
                    }.sortedBy { it.listId }
                    recyclerView.adapter = GroupedViewAdapter(sortedGroupedItems)
                }
            }

            override fun onFailure(call: Call<List<FetchResponse>>, t: Throwable) {
                // Handle error
            }
        })
    }
}