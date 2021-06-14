package tj.colibri.avrang.data.cities

import com.google.gson.annotations.SerializedName


data class Cities (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
)