package tj.colibri.avrang.data.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_table")
data class CartItem(


    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cart_id")
    @SerializedName("id") val id : Int,

    @ColumnInfo(name = "cart_slug")
    @SerializedName("slug") val slug : String,

    @ColumnInfo(name = "cart_title")
    @SerializedName("name") val name : String,

    @ColumnInfo(name = "cart_quantity")
    @SerializedName("quantity") var quantity : Int,

    @ColumnInfo(name = "cart_images")
    @SerializedName("images") val images : String,

    @ColumnInfo(name = "cart_unit_price")
    @SerializedName("price") val price : Double,

    @ColumnInfo(name = "cart_bonus")
    @SerializedName("bonus") val bonus : Int

)
