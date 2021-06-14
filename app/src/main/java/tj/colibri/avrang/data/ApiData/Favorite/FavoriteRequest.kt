package tj.colibri.avrang.data.ApiData.Favorite

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.product.ProductCard2

data class FavoriteRequest (@SerializedName("favorites") val favorites : List<ProductCard2>
    )