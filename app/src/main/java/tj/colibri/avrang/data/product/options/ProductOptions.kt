package tj.colibri.avrang.data.product.options

data class ProductOptions(
    val id : Int,
    val title : String,
    val slug : String,
    val options : List<Option>
)