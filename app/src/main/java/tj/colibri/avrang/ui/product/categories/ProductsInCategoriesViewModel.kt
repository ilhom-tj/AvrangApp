package tj.colibri.avrang.ui.product.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.Category.CategoryProductRequest
import tj.colibri.avrang.data.ApiData.filter.FilterData
import tj.colibri.avrang.data.ApiData.product.ProductCard2
import tj.colibri.avrang.network.repositories.catalogRepo.CatalogRepo
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.utils.Features

class ProductsInCategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FavoriteRepository(application)
    private val catalogRepository = CatalogRepo(application)


    fun search(filterData: FilterData) : LiveData<CategoryProductRequest>{
        return catalogRepository.searchProduct(filterData)
    }

    fun addFavorite(favorite: ProductCard2) = GlobalScope.launch{

        repo.addItemToFavorite(Features().toFavoriteCache(favorite))
    }
    fun deleteFavorite(favorite: ProductCard2) = GlobalScope.launch {
        repo.deleteFavorite(Features().toFavoriteCache(favorite))
    }

}