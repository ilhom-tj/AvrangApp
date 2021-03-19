package tj.colibri.avrang.data.ApiData.Cart

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.cart.CartItemResponse

data class UpdateCart (
    @SerializedName("cartData") val cartData : MutableList<CartItemResponse>
)