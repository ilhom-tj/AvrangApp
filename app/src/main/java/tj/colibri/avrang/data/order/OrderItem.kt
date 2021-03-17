package tj.colibri.avrang.data.order

data class OrderItem(
    val id : Int,
    val title : String,
    val code: String,
    val unit_price: Double,
    val quantity : Int,
    val bonus : Int
)
