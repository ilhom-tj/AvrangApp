package tj.colibri.avrang.models.Registration

import com.google.gson.annotations.SerializedName

data class ConfirmCode (
    @SerializedName("confirm_code") val confirm_code : Int,
    @SerializedName("confirmed") val confirmed : Boolean,
    @SerializedName("message") val message : String
)