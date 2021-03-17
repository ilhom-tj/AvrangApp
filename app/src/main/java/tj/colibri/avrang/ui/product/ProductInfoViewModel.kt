package tj.colibri.avrang.ui.product

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.Cart.Cart
import tj.colibri.avrang.data.ApiData.product.ProductInformation
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.network.repositories.products.ProductRepo

class ProductInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FavoriteRepository(application)
    private val productRepo = ProductRepo(application.applicationContext)
    private val cartRepository = CartRepository(application.applicationContext)


    fun addFavorite(favorite: Favorite) = GlobalScope.launch{
        repo.addItemToFavorite(favorite)
    }
    fun deleteFavorite(favorite: Favorite) = GlobalScope.launch {
        repo.deleteFavorite(favorite)
    }

    fun getProductBySlug(slug : String): LiveData<ProductInformation>{
        return productRepo.getProduct(slug)
    }

    fun addToCart(id : Int,quantity : Int) : LiveData<Cart>{

        return cartRepository.addToCart(id,quantity)
    }
}