package tj.colibri.avrang.models.Home

import com.google.gson.annotations.SerializedName

data class Rating (

	@SerializedName("rating") val rating : Int,
	@SerializedName("quantity") val quantity : Int,
	@SerializedName("is_rated") val is_rated : Boolean
)