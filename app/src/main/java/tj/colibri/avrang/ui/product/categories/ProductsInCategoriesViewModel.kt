package tj.colibri.avrang.ui.product.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository

class ProductsInCategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FavoriteRepository(application)

    fun addFavorite(favorite: Favorite) = GlobalScope.launch{

        repo.addItemToFavorite(favorite)
    }
    fun deleteFavorite(favorite: Favorite) = GlobalScope.launch {
        repo.deleteFavorite(favorite)
    }
}