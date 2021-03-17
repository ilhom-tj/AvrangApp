package tj.colibri.avrang.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application
) {

    private val repo  = FavoriteRepository(application)
    val favList = repo.favList

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }

    fun deleteFavorite(favorite: Favorite){
        repo.deleteFavorite(favorite)
    }

    val text: LiveData<String> = _text
}