package tj.colibri.avrang.models.Product.ProductInfo

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.models.Home.Labels
import tj.colibri.avrang.models.Product.Rating


data class Product (
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
	@SerializedName("is_favorite") val is_favorite : Boolean,
	@SerializedName("labels") val labels : Labels,
	@SerializedName("discounted_price") val discounted_price : Double,
	@SerializedName("offers") val offers : String,
	@SerializedName("gifts") val gifts : List<String>
)