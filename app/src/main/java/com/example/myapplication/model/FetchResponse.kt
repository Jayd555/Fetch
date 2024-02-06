package com.example.myapplication.model

data class GroupedItem(
	val listId: Int,
	val items: List<FetchResponse>
)

data class FetchResponse(
	val id: Int,
	val name: String?,
	val listId: Int
)
