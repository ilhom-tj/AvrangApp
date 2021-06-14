package tj.colibri.avrang.data.ApiData.orderDetails

import com.google.gson.annotations.SerializedName

data class Delivers(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("price") val price : Int,
    @SerializedName("is_default") val is_default : Int
)