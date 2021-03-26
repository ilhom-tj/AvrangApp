package tj.colibri.avrang.ui.product.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.utils.Features

class ProductsInCategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FavoriteRepository(application)

    fun addFavorite(favorite: ProductCard2) = GlobalScope.launch{

        repo.addItemToFavorite(Features().toFavoriteCache(favorite))
    }
    fun deleteFavorite(favorite: ProductCard2) = GlobalScope.launch {
        repo.deleteFavorite(Features().toFavoriteCache(favorite))
    }
}