package tj.colibri.avrang.data.ApiData.Installment

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import tj.colibri.avrang.data.cart.CartItem

data class PayClass(
    @SerializedName("products")
    var products : List<CheckOutItem>,
    @SerializedName("name")
    var name : String,
    @SerializedName("phone")
    var phone : String,
    @SerializedName("installment")
    var installment: Int
)