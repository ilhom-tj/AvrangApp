package tj.colibri.avrang.data.ApiData.Cart

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.cart.CartItemResponse
import tj.colibri.avrang.data.mock.ProductCard2

data class Cart (
    @SerializedName("cart") val cartData : MutableList<CartItemResponse>
)