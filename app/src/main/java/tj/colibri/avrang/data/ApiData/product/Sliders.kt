package tj.colibri.avrang.data.ApiData.product

import com.google.gson.annotations.SerializedName


data class Sliders (
	@SerializedName("id") val id : Int,
	@SerializedName("image") val image : String,
	@SerializedName("mob_image") val mob_image : String,
	@SerializedName("url") val url : String
)