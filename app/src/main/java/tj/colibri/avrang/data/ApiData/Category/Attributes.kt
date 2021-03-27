package tj.colibri.avrang.data.ApiData.Category

import com.google.gson.annotations.SerializedName


data class Attributes (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("deleted_at") val deleted_at : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
)