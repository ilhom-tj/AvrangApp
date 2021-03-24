package tj.colibri.avrang.data.ApiData.home

import com.google.gson.annotations.SerializedName


data class Categories (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("icon") val icon : String,
	@SerializedName("slug") val slug : String
)