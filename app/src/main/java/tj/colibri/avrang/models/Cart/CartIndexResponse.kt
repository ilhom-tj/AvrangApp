package tj.colibri.avrang.models.Cart

import com.google.gson.annotations.SerializedName

data class CartIndexResponse (
    @SerializedName("products") val products : List<Int>
        )