package tj.colibri.avrang.data

import tj.colibri.avrang.data.user.User
import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.orderDetails.Delivers
import tj.colibri.avrang.data.ApiData.orderDetails.Store
import tj.colibri.avrang.data.ApiData.product.Banks

data class OrderDetails  (
    @SerializedName("user") val user : User,
    @SerializedName("banks") val banks : List<Banks>,
    @SerializedName("selected_bank") val selected_bank : String,
    @SerializedName("store") val store : Store,
    @SerializedName("deliveries") val delivers: List<Delivers>
        )