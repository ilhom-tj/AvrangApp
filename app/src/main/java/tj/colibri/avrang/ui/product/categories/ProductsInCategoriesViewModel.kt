package tj.colibri.avrang.ui.product.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.Category.CategoryProductRequest
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.repositories.catalogRepo.CatalogRepo
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.utils.Features

class ProductsInCategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FavoriteRepository(application)
    private val catalogRepository = CatalogRepo(application)

    fun getCategoryProudctsIndex(slug : String,page : Int) : LiveData<CategoryProductRequest>{
        return catalogRepository.getCategoryProductsBySlug(slug,page)
    }

    fun getCategoryProudcts(slug : String) : LiveData<CategoryProductRequest>{
        return catalogRepository.getCategoryProductsBySlug(slug,1)
    }

    fun addFavorite(favorite: ProductCard2) = GlobalScope.launch{

        repo.addItemToFavorite(Features().toFavoriteCache(favorite))
    }
    fun deleteFavorite(favorite: ProductCard2) = GlobalScope.launch {
        repo.deleteFavorite(Features().toFavoriteCache(favorite))
    }
}