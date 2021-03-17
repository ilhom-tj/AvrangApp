package tj.colibri.avrang.data.ApiData.registration

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.user.User

data class RegistrationCallBack (

    @SerializedName("access_token") val access_token : String,
    @SerializedName("token_type") val token_type : String,
    @SerializedName("expires_in") val expires_in : Int,
    @SerializedName("user") val user : User
)