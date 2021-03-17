package tj.colibri.avrang.data.ApiData.home

import com.google.gson.annotations.SerializedName


data class Partners (

	@SerializedName("id") val id : Int,
	@SerializedName("image") val image : String,
	@SerializedName("url") val url : String,
	@SerializedName("slider_at") val slider_at : String,
	@SerializedName("deleted_at") val deleted_at : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
)