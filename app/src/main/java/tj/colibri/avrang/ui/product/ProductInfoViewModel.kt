package tj.colibri.avrang.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.Cart.Cart
import tj.colibri.avrang.data.ApiData.product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.network.repositories.products.ProductRepo

class ProductInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FavoriteRepository(application)
    private val productRepo = ProductRepo(application.applicationContext)
    private val cartRepository = CartRepository(application.applicationContext)

    val cartIndexs = cartRepository.cartIndexes

    fun addFavorite(favorite: Favorite) = GlobalScope.launch{
        repo.addItemToFavorite(favorite)
    }
    fun deleteFavorite(favorite: Favorite) = GlobalScope.launch {
        repo.deleteFavorite(favorite)
    }

    fun getProductBySlug(slug : String): LiveData<ProductInfortmation2>{
        return productRepo.getProduct(slug)
    }

    fun addToCart(id : Int) : Boolean{
        return cartRepository.addToCart(id).isCompleted
    }

    fun addToLocalCart(cartItem: CartItem){
        cartRepository.addToCart(cartItem)
    }
    fun updateCart(list: MutableList<Int>){
        cartRepository.updateCart(list)
    }
}