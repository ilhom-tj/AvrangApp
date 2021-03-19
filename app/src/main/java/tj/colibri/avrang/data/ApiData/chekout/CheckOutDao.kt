package tj.colibri.avrang.data.ApiData.chekout

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CheckOutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCheckOutItem(checkOutItem: CheckOutItem)

    @Query("SELECT * FROM checkout_table")
    fun getAllItems() : LiveData<List<CheckOutItem>>

    @Delete
    fun deleteItem(checkOutItem: CheckOutItem)

    @Query("DELETE FROM checkout_table WHERE id=:id")
    fun deleteById(id:Int)
}