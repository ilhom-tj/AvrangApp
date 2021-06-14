package tj.colibri.avrang.DataBase.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import tj.colibri.avrang.models.Cart.CartIndex

@Dao
interface CartIndexDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemIndex(index : CartIndex)

    @Query("SELECT id FROM cart_indexes")
    fun getCartIndexes() : LiveData<List<Int>>

    @Query("DELETE FROM cart_indexes WHERE id=:id")
    fun deleteIndex(id: Int)

    @Query("DELETE  FROM CART_INDEXES")
    fun deleteAllIndexes()
}