package tj.colibri.avrang.data.Favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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