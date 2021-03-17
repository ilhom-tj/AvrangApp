package tj.colibri.avrang.data.product.options.info

import tj.colibri.avrang.data.product.options.ProductOptions
import tj.colibri.avrang.data.product.specifications.Specification
import tj.colibri.avrang.data.slider.SliderItem

data class ProductInfo (
    val id : Int,
    val productCode : Int,
    val productName : String,
    val productPrice : Double,
    val productRating : Double,
    val ratings_qty : Int,
    val productOldPrice : Double,
    val productAbout : String,
    val productRatingFive : Int,
    val productRatingFour : Int,
    val productRatingThree: Int,
    val productRatingTwo: Int,
    val productRatingOne: Int,
    val sliderImages : List<SliderItem>,
    val optionsList : List<ProductOptions>,
    val specsList : List<Specification>,
    )