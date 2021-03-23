package tj.colibri.avrang.data.ApiData.chekout

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "checkout_table")
@Parcelize
data class CheckOutItem(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "checkout_quantity")
    @SerializedName("quantity")
    var quantity: Int


) : Parcelable