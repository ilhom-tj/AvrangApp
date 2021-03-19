package tj.colibri.avrang.data.ApiData.product.ProductInfo

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.home.Rating


data class Similar (
	@SerializedName("id") val id : Int,
	@SerializedName("SKU") val sKU : Int,
	@SerializedName("name") val name : String,
	@SerializedName("price") val price : Double,
	@SerializedName("excerpt") val excerpt : String,
	@SerializedName("category") val category : Category,
	@SerializedName("images") val images : List<String>,
	@SerializedName("slug") val slug : String,
	@SerializedName("city_id") val city_id : Int,
	@SerializedName("brand_id") val brand_id : Int,
	@SerializedName("rating") val rating : Rating,
	@SerializedName("discounted_price") val discounted_price : String,
	@SerializedName("offers") val offers : String
)