package tj.colibri.avrang.data.ApiData.chekout.forRequest

import com.google.gson.annotations.SerializedName

data class CheckOutBankResponse (
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("redirect")
    val redirect : String
    )