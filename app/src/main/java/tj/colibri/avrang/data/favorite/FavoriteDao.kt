package tj.colibri.avrang.data.favorite

import androidx.lifecycle.LiveData
import androidx.room.*
import tj.colibri.avrang.data.mock.ProductCard2

//@Dao
//interface FavoriteDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(favorite: FavoriteCard)
//
//    @Query("SELECT * FROM products_favorites")
//    fun getAllFavorites() : LiveData<List<FavoriteCard>>
//
//    @Delete
//    suspend fun deleteFavorite(favorite: FavoriteCard)
//}