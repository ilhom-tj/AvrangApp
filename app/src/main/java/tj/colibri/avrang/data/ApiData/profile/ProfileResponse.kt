package tj.colibri.avrang.data.ApiData.profile

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.user.User

data class ProfileResponse (

    @SerializedName("user") val user : User,
    @SerializedName("orders") val orders : List<String>,
    @SerializedName("favorites") val favorites : List<String>
)