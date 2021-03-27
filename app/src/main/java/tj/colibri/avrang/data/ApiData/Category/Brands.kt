package tj.colibri.avrang.data.ApiData.Category

import com.google.gson.annotations.SerializedName


data class Brands (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("image") val image : String
)