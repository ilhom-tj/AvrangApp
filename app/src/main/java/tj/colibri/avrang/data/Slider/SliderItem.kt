package tj.colibri.avrang.data.Slider

import com.google.gson.annotations.SerializedName

data class SliderItem(
    @SerializedName("id") var id : Int,
    @SerializedName("image") var image : String,
    @SerializedName("mob_image") var mob_image : String,
    @SerializedName("url") var url : String
)