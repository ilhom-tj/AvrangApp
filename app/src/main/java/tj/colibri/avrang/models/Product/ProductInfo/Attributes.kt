package tj.colibri.avrang.models.Product.ProductInfo

import com.google.gson.annotations.SerializedName

data class Attributes (

	@SerializedName("name") val name : String,
	@SerializedName("value") val value : String
)