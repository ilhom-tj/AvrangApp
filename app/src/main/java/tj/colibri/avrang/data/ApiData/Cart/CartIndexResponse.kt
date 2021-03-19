package tj.colibri.avrang.data.ApiData.Cart

import com.google.gson.annotations.SerializedName

data class CartIndexResponse (
    @SerializedName("products") val products : List<Int>
        )