package tj.colibri.avrang.data.ApiData.home

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.ApiData.product.ProductCard2

data class HomeResponse (

    @SerializedName("newProducts") val newProducts : List<ProductCard2>,
    @SerializedName("popular") val popular : List<ProductCard2>,
    @SerializedName("maxDiscounts") val maxDiscounts : List<ProductCard2>,
    @SerializedName("news") val news : List<News>,
    @SerializedName("partners") val partners : List<Partners>,
    @SerializedName("categories") val categories : List<Categories>,
    @SerializedName("banners") val banners : List<Banners>,
    @SerializedName("sliders") val sliders : List<Sliders>,
    @SerializedName("promotions") val promotions : List<ProductCard2>,
    @SerializedName("recomended") val recomended : List<ProductCard2>
)