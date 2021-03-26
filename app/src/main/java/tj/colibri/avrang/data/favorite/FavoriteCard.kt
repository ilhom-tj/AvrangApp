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

@Entity(tableName = "favorite_table")
data class FavoriteCard(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    @ColumnInfo(name = "favorite_name")
    val name : String,
    @ColumnInfo(name = "favorite_slug")
    val slug : String,
    @ColumnInfo(name = "favorite_sku")
    val sku : Int,
    @ColumnInfo(name = "favorite_price")
    val price : Double,
    @ColumnInfo(name = "favorite_discounted")
    val discounted : Double,
    @ColumnInfo(name = "favorite_image")
    val image : String
)