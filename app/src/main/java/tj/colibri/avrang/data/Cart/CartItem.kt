package tj.colibri.avrang.data.Cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_table")
data class CartItem(
    @PrimaryKey(autoGenerate = false)
    val id : Int,

    @ColumnInfo(name = "cart_slug")
    val slug : String,

    @ColumnInfo(name = "cart_sku")
    val sku : String,

    @ColumnInfo(name = "cart_title")
    val name : String,

    @ColumnInfo(name = "cart_quantity")
    var quantity : Int = 1,

    @ColumnInfo(name = "cart_images")
    val images : String,

    @ColumnInfo(name = "cart_unit_price")
    val price : Double,

    @ColumnInfo(name = "cart_bonus")
    val bonus : Int,

    @ColumnInfo(name = "cart_in_stock") var in_stock : Int

)

data class CartItemResponse(

    @SerializedName("id") val id : Int,

    @SerializedName("slug") val slug : String,

    @SerializedName("SKU") val  sku : String,

    @SerializedName("name") val name : String,

    @SerializedName("quantity") var quantity : Int,

    @SerializedName("images") val images : List<String>,

    @SerializedName("price") val price : Double,

    @SerializedName("bonus") val bonus : Int,

    @SerializedName("in_stock") val in_stock : Int

)
