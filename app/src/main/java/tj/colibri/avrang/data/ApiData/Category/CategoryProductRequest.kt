package tj.colibri.avrang.data.ApiData.Category

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.product.ProductInfo.Banks

data class CategoryProductRequest(
    @SerializedName("categories") val categories : List<Categories>,
    @SerializedName("products") val products : ProductData,
    @SerializedName("priceRangeFrom") val priceRangeFrom : Double,
    @SerializedName("priceRangeTo") val priceRangeTo : Double,
    @SerializedName("attributes") val attributes : List<Attributes>,
    @SerializedName("brands") val brands : List<Brands>,
    @SerializedName("banks") val banks : List<Banks>,
)