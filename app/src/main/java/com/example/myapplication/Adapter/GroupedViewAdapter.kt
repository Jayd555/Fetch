package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.GroupedItem

class GroupedViewAdapter(private val items: List<GroupedItem>) : RecyclerView.Adapter<GroupedViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listIDTextView: TextView = itemView.findViewById(R.id.listIdTextView)
        val recyclerView1: RecyclerView = itemView.findViewById(R.id.recyclerView1)
        init {
            recyclerView1.layoutManager = LinearLayoutManager(itemView.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grouped_view_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val groupedItem = items[position]
        holder.listIDTextView.text = "List ${groupedItem.listId}"
        val itemsToDisplay = groupedItem.items
        val itemAdapter = ViewAdapter(itemsToDisplay)
        holder.recyclerView1.adapter = itemAdapter
    }

    override fun getItemCount(): Int {
        return items.size
    }
}