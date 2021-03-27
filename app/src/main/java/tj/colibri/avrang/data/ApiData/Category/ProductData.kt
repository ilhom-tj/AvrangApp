package tj.colibri.avrang.data.ApiData.Category

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.mock.ProductCard2

data class ProductData(
    @SerializedName("data") val products : List<ProductCard2>,
    @SerializedName("links") val links : Links
)
