package tj.colibri.avrang.models.Home

import com.google.gson.annotations.SerializedName


data class Banners (
	@SerializedName("id") val id : Int,
	@SerializedName("image") val image : String,
	@SerializedName("mob_image") val mob_image : String,
	@SerializedName("url") val url : String
)