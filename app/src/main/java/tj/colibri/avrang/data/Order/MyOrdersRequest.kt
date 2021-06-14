package tj.colibri.avrang.data.Order

import com.google.gson.annotations.SerializedName

data class MyOrdersRequest (
	@SerializedName("orders") val orders : List<OrderContainer>
)