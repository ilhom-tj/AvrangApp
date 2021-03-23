package tj.colibri.avrang.data.ApiData.chekout.forRequest

import com.google.gson.annotations.SerializedName

data class CheckOutResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("message") val message : String
        )