package tj.colibri.avrang.data.ApiData.chekout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkout_table")
data class CheckOutItem (
    @PrimaryKey(autoGenerate = false) val id : Int,
    @ColumnInfo(name = "checkout_quantity") var quantity : Int
        )