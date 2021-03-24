package tj.colibri.avrang.data.favorite

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tj.colibri.avrang.data.ApiData.home.Labels
import tj.colibri.avrang.data.ApiData.home.Rating

//@Entity(tableName = "products_favorites")
//
//@Parcelize
//data class FavoriteCard(
//    @PrimaryKey(autoGenerate = false)
//    val id: Int,
//    @ColumnInfo(name = "product_name")
//    val name: String,
//    @ColumnInfo(name = "product_sku")
//    val sKU: Int?,
//    @ColumnInfo(name = "product_price")
//    val productPrice: Double,
//    @ColumnInfo(name = "product_favorite")
//    var isFavorite: Boolean,
//    @ColumnInfo(name = "product_exerpt")
//    var excerpt: String,
//    @Embedded
//    @ColumnInfo(name ="product_rating")
//    val rating : Rating,
//    @ColumnInfo(name = "product_label")
//    @Embedded
//    val labels : Labels,
//    @ColumnInfo(name = "product_slug")
//    val slug : String,
//    @ColumnInfo(name = "product_dis_price")
//    val newPrice: Double,
//    @ColumnInfo(name = "product_images")
//    val images: String,
//    @ColumnInfo(name = "product_quantity")
//    var quantity : Int,
//    @ColumnInfo(name = "product_bonus")
//    var bonus : Int,
//    @ColumnInfo(name="product_category")
//    var type : Int
//): Parcelable