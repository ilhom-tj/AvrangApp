package tj.colibri.avrang.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tj.colibri.avrang.data.ApiData.Favorite.FavoriteRequest
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application
) {

    private val repo  = FavoriteRepository(application)


    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }

    fun getFavorites() : LiveData<FavoriteRequest>{
        return  repo.getFavorite()
    }
    fun deleteFavorite(favorite: ProductCard2) : LiveData<Boolean>{
        return repo.deleteFavorite(favorite)
    }

    val text: LiveData<String> = _text
}