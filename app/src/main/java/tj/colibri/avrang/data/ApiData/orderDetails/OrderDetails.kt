package tj.colibri.avrang.data.ApiData.orderDetails

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.product.Banks
import tj.colibri.avrang.data.user.User

data class OrderDetails  (
    @SerializedName("user") val user : User,
    @SerializedName("banks") val banks : List<Banks>,
    @SerializedName("selected_bank") val selected_bank : String,
    @SerializedName("store") val store : Store
        )