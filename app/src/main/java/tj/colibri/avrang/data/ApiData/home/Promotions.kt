package tj.colibri.avrang.data.ApiData.home

import com.google.gson.annotations.SerializedName

data class Promotions (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("price") val price : Double,
    @SerializedName("discounted_price") val discounted_price : Int,
    @SerializedName("images") val images : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("rating") val rating : Rating,
    @SerializedName("is_favorite") val is_favorite : Boolean,
    @SerializedName("labels") val labels : Labels
)