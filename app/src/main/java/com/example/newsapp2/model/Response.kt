package com.example.newsapp.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("sources")
	val tabs: List<TabsDM>? = null,

	@field:SerializedName("status")
	val status: String? = null,
	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("message")
	val message: String? = null


)

data class TabsDM(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
