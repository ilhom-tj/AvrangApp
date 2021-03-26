package tj.colibri.avrang.data.order

import com.google.gson.annotations.SerializedName

data class OrderContainer(
    @SerializedName("id") val id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("total") val total : Double,
    @SerializedName("comments") val comments : String,
    @SerializedName("status") val status : Status,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("products") val products : List<Products>
)