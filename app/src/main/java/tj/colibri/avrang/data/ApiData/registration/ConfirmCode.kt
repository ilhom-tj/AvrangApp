package tj.colibri.avrang.data.ApiData.registration

import com.google.gson.annotations.SerializedName

data class ConfirmCode (
    @SerializedName("confirm_code") val confirm_code : Int,
    @SerializedName("confirmed") val confirmed : Boolean
)