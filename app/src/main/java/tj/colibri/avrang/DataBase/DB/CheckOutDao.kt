package tj.colibri.avrang.DataBase.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import tj.colibri.avrang.models.Chekout.CheckOutItem

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

    @Query("DELETE FROM checkout_table")
    fun deleteAllIndexes()
}