package tj.colibri.avrang.models.Product.ProductInfo

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.models.Product.Banks
import tj.colibri.avrang.models.Product.Reviews
import tj.colibri.avrang.models.Product.ProductCard2


data class ProductInfortmation2 (
    @SerializedName("product") val product : ProductCard2,
    @SerializedName("attributes") val attributes : List<Attributes>,
    @SerializedName("reviews") val reviews : List<Reviews>?,
    @SerializedName("related") val related : List<String>,
    @SerializedName("alsoBought") val alsoBought : List<ProductCard2>,
    @SerializedName("banks") val banks : List<Banks>,
    @SerializedName("similar") val similar : List<Similar>
)