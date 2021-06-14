package tj.colibri.avrang.models.Profile

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.User.User

data class ProfileResponse (

    @SerializedName("user") val user : User,
    @SerializedName("orders") val orders : List<String>,
    @SerializedName("favorites") val favorites : List<String>
)