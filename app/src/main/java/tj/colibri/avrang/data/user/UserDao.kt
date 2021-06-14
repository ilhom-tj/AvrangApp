package tj.colibri.avrang.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM table_user")
    fun getUser() : LiveData<User>

    @Query("DELETE FROM table_user")
    fun cleanUsers() : Int
}