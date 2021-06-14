package tj.colibri.avrang.models.Cart

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.Cart.CartItemResponse

data class Cart (
    @SerializedName("cart") val cartData : MutableList<CartItemResponse>
)