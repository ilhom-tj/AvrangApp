package tj.colibri.avrang.models.OrderDetails

import com.google.gson.annotations.SerializedName


data class Store (

    @SerializedName("id") val id : Int,
    @SerializedName("address") val address : String,
    @SerializedName("city_id") val city_id : Int,
    @SerializedName("contact_center") val contact_center : Contact_center,
    @SerializedName("service_center") val service_center : Service_center,
    @SerializedName("map") val map : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String
)