package tj.colibri.avrang.data.ApiData.home

import com.google.gson.annotations.SerializedName

data class Rating (

	@SerializedName("rating") val rating : Int,
	@SerializedName("quantity") val quantity : Int,
	@SerializedName("is_rated") val is_rated : Boolean
)