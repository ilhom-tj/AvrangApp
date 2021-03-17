package tj.colibri.avrang.data.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_favorites")
data class Favorite (
    @PrimaryKey(autoGenerate = false)
    val id : Int,

    @ColumnInfo(name = "favorite_title")
    val title : String,

    @ColumnInfo(name = "favorite_code")
    val code: String,

    @ColumnInfo(name = "favorite_price")
    val price: Double,

    @ColumnInfo(name = "favorite_oldPrice")
    val oldPrice : Double
)