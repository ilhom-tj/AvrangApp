package tj.colibri.avrang.models.Cart

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.Cart.CartItemResponse

data class UpdateCart (
    @SerializedName("cartData") val cartData : MutableList<CartItemResponse>
)