package tj.colibri.avrang.data.mock

import androidx.room.*
import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.ApiData.home.Labels
import tj.colibri.avrang.data.ApiData.home.Rating
import java.util.*

data class ProductCard2(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Int,

    @ColumnInfo(name = "product_name")
    @SerializedName("name") val name: String,

    @ColumnInfo(name = "product_sku")
    @SerializedName("SKU")          val sKU: Int,

    @ColumnInfo(name = "product_price")
    @SerializedName("price") val productPrice: Double,

    @ColumnInfo(name = "product_favorite")
    @SerializedName("is_favorite")  var isFavorite: Boolean,

    @SerializedName("rating")
    @Embedded
    val rating : tj.colibri.avrang.data.ApiData.product.Rating,

    @SerializedName("labels")
    @Embedded
    val labels : Labels,

    @ColumnInfo(name = "product_slug")

    @SerializedName("slug")          val slug : String,

    @ColumnInfo(name = "product_dis_price")
    @SerializedName("discounted_price")  val newPrice: Double,

    @ColumnInfo(name = "product_images")
    @SerializedName("images")  val images: String,

    @ColumnInfo(name = "product_quantity")
    @SerializedName("quantity") var quantity : Int,


    @ColumnInfo(name = "product_bonus")
    @SerializedName("bonus") var bonus : Int,

    @ColumnInfo(name="product_category")
    var type : Int

)