package tj.colibri.avrang.DataBase.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import tj.colibri.avrang.data.Favorite.FavoriteCard

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteCard)

    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites() : LiveData<List<FavoriteCard>>

    @Query("SELECT COUNT(id) FROM favorite_table")
    fun getFavCount() : LiveData<Int>

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteCard)
}