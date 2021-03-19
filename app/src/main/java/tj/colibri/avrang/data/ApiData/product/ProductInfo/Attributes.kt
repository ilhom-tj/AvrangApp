package tj.colibri.avrang.data.ApiData.product.ProductInfo

import com.google.gson.annotations.SerializedName

data class Attributes (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)