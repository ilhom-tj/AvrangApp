package tj.colibri.avrang.data.order

import com.google.gson.annotations.SerializedName



data class Pivot (

	@SerializedName("order_id") val order_id : Int,
	@SerializedName("product_id") val product_id : Int,
	@SerializedName("quantity") val quantity : Int,
	@SerializedName("price") val price : Double,
	@SerializedName("bonus") val bonus : Int
)