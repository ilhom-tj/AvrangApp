package tj.colibri.avrang.models.Chekout.forRequest

import com.google.gson.annotations.SerializedName

data class CheckOutBankResponse (
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("redirect")
    val redirect : String
    )