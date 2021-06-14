package tj.colibri.avrang.data.Cities

import com.google.gson.annotations.SerializedName


data class Cities (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
)