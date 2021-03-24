package tj.colibri.avrang.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.home.HomeResponse
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.network.repositories.homeRepo.HomeRepo

class HomeViewModel(application: Application) : AndroidViewModel(application) {


    private val repo = FavoriteRepository(application)
    private val homeRepo = HomeRepo(application.applicationContext)

//    val newProducts = homeRepo.getNewProducts
//    val popularProducts = homeRepo.getPopularProducts
//    val recommendedProducts = homeRepo.getRecommendedProducts
//    val maxDisProducts = homeRepo.getMaxDiscountProducts

    fun addFavorite(favorite: ProductCard2) = GlobalScope.launch{
        repo.addItemToFavorite(favorite)
    }
    fun deleteFavorite(favorite: ProductCard2) = GlobalScope.launch {
        repo.deleteFavorite(favorite)
    }

    fun getHome() : LiveData<HomeResponse>{
        return homeRepo.getHome()
    }

}