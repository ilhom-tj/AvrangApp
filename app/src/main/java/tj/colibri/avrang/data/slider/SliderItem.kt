package tj.colibri.avrang.data.slider

import com.google.gson.annotations.SerializedName

data class SliderItem(
    @SerializedName("id") val id : Int,
    @SerializedName("image") val image : String,
    @SerializedName("mob_image") val mob_image : String,
    @SerializedName("url") val url : String
)