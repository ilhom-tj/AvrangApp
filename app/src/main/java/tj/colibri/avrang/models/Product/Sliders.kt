package tj.colibri.avrang.models.Product

import com.google.gson.annotations.SerializedName


data class Sliders (
	@SerializedName("id") var id : Int,
	@SerializedName("image") var image : String,
	@SerializedName("mob_image") var mob_image : String,
	@SerializedName("url") var url : String
)