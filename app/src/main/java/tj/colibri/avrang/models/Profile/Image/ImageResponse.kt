package tj.colibri.avrang.models.Profile.Image

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("image") val image : String
)