package tj.colibri.avrang.data.ApiData.Contact

import com.google.gson.annotations.SerializedName


data class Contacts (

    @SerializedName("id") val id : Int,
    @SerializedName("address") val address : String,
    @SerializedName("city") val city : City,
    @SerializedName("contact_center") val contact_center : Contact_center,
    @SerializedName("service_center") val service_center : Contact_center,
    @SerializedName("map") val map : String,
    @SerializedName("is_current") val is_current : Boolean
)