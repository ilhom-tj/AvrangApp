package tj.colibri.avrang.data.favorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM products_favorites")
    fun getAllFavorites() : LiveData<List<Favorite>>

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}