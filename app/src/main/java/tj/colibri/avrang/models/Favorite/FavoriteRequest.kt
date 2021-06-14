package tj.colibri.avrang.models.Favorite

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.models.Product.ProductCard2

data class FavoriteRequest (@SerializedName("favorites") val favorites : List<ProductCard2>
    )