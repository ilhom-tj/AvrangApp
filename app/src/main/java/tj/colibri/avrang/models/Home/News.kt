package tj.colibri.avrang.models.Home

import com.google.gson.annotations.SerializedName



data class News (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("image") val image : String,
	@SerializedName("description") val description : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("post_type") val post_type : Int,
	@SerializedName("deleted_at") val deleted_at : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
)