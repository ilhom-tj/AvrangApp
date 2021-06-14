package tj.colibri.avrang.data.Product.options

data class ProductOptions(
    val id : Int,
    val title : String,
    val slug : String,
    val options : List<Option>
)