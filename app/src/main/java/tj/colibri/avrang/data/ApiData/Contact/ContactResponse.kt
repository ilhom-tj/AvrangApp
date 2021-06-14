package tj.colibri.avrang.data.ApiData.Contact

import com.google.gson.annotations.SerializedName


data class ContactResponse (

	@SerializedName("contacts") val contacts : List<Contacts>
)