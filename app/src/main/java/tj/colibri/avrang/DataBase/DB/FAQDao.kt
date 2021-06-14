package tj.colibri.avrang.DataBase.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tj.colibri.avrang.models.FAQ.FAQs

@Dao
interface FAQDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFaq(faQs: FAQs)

    @Query("SELECT * FROM faq_table")
    fun getAllFAQs() : LiveData<List<FAQs>>

}