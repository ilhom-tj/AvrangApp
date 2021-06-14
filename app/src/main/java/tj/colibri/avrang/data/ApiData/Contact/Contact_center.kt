package tj.colibri.avrang.data.ApiData.Contact

import com.google.gson.annotations.SerializedName

data class Contact_center (

	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : String,
	@SerializedName("working_time") val working_time : String
)