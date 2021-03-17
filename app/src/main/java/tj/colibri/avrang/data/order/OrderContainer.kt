package tj.colibri.avrang.data.order

data class OrderContainer(
    val orderNumber : Int,
    val orderDate : String,
    val status : String,
    val oreders : List<OrderItem>
)