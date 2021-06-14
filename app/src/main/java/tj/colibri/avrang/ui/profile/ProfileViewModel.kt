package tj.colibri.avrang.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.models.Product.ProductCard2
import tj.colibri.avrang.network.repositories.userRepo.UserRepository
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.utils.Features

class ProfileViewModel(application: Application) : AndroidViewModel(application){

    private val repo = FavoriteRepository(application)
    private val userRepo = UserRepository(application)


    val user = userRepo.getUser

    fun addFavorite(favorite: ProductCard2) = GlobalScope.launch{

        repo.addItemToFavorite(Features().toFavoriteCache(favorite))
    }
    fun deleteFavorite(favorite: ProductCard2) = GlobalScope.launch {
        repo.deleteFavorite(Features().toFavoriteCache(favorite))
    }

    val profile =  userRepo.getProfile()
}