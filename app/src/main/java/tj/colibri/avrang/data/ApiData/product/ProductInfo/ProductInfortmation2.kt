package tj.colibri.avrang.data.ApiData.product.ProductInfo

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.product.Banks
import tj.colibri.avrang.data.ApiData.product.Reviews
import tj.colibri.avrang.data.mock.ProductCard2


data class ProductInfortmation2 (
	@SerializedName("product") val product : Product,
	@SerializedName("attributes") val attributes : List<Attributes>,
	@SerializedName("reviews") val reviews : List<Reviews>,
	@SerializedName("related") val related : List<String>,
	@SerializedName("alsoBought") val alsoBought : List<ProductCard2>,
	@SerializedName("banks") val banks : List<Banks>,
	@SerializedName("similar") val similar : List<Similar>
)