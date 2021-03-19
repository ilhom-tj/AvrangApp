package tj.colibri.avrang.data.ApiData.Cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_indexes")
data class CartIndex(
    @PrimaryKey(autoGenerate = false) val id: Int
)