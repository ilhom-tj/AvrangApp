package tj.colibri.avrang.DataBase.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tj.colibri.avrang.data.User.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM table_user")
    fun getUser() : LiveData<User>

    @Query("DELETE FROM table_user")
    fun cleanUsers() : Int
}