package tj.colibri.avrang.data.ApiData.FAQ

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FAQDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFaq(faQs: FAQs)

    @Query("SELECT * FROM faq_table")
    fun getAllFAQs() : LiveData<List<FAQs>>

}