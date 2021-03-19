package tj.colibri.avrang.data.ApiData.Cart

import androidx.lifecycle.LiveData
import androidx.room.*

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