package tj.colibri.avrang.models.Registration

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.User.User

data class RegistrationCallBack (

    @SerializedName("access_token") val access_token : String,
    @SerializedName("token_type") val token_type : String,
    @SerializedName("expires_in") val expires_in : Int,
    @SerializedName("user") val user : User,
    @SerializedName("message") val message : String
)