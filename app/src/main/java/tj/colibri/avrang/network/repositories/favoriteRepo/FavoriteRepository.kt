package tj.colibri.avrang.network.repositories.favoriteRepo

import android.app.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.favorite.FavoriteDB

class FavoriteRepository(application: Application) {

    private val favoriteDao = FavoriteDB.getInstance(application.applicationContext).favorite

    //Favorite Live Data
    var favList = favoriteDao.getAllFavorites()



    fun addItemToFavorite(favorite: Favorite) = GlobalScope.launch{
        favoriteDao.insert(favorite)
    }
    fun deleteFavorite(favorite: Favorite) = GlobalScope.launch {
        favoriteDao.deleteFavorite(favorite)
    }
}