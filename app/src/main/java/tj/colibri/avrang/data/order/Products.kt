package tj.colibri.avrang.data.order

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.order.Pivot



data class Products (

	@SerializedName("id") val id : Int,
	@SerializedName("parent_id") val parent_id : Int,
	@SerializedName("SKU") val sKU : Int,
	@SerializedName("name") val name : String,
	@SerializedName("price") val price : Double,
	@SerializedName("category_id") val category_id : Int,
	@SerializedName("quantity") val quantity : Int,
	@SerializedName("excerpt") val excerpt : String,
	@SerializedName("description") val description : String,
	@SerializedName("images") val images : List<String>,
	@SerializedName("slug") val slug : String,
	@SerializedName("city_id") val city_id : Int,
	@SerializedName("brand_id") val brand_id : Int,
	@SerializedName("offers") val offers : String,
	@SerializedName("bonus") val bonus : Int,
	@SerializedName("is_hit") val is_hit : Int,
	@SerializedName("deleted_at") val deleted_at : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String,
	@SerializedName("pivot") val pivot : Pivot
)