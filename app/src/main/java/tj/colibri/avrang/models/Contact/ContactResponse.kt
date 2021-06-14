package tj.colibri.avrang.models.Contact

import com.google.gson.annotations.SerializedName


data class ContactResponse (

	@SerializedName("contacts") val contacts : List<Contacts>
)