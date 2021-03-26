package tj.colibri.avrang.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import tj.colibri.avrang.data.ApiData.product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.network.repositories.products.ProductRepo
import tj.colibri.avrang.utils.Features

class ProductInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val favRepo = FavoriteRepository(application)
    private val productRepo = ProductRepo(application.applicationContext)
    private val cartRepository = CartRepository(application.applicationContext)

    val favList = favRepo.favList

    val checkOut = cartRepository.checkOutList
    val cartIndexs = cartRepository.cartIndexes

    fun addFavorite(favorite: ProductCard2) = GlobalScope.launch{
        favRepo.addItemToFavorite(Features().toFavoriteCache(favorite))
    }
    fun deleteFavorite(favorite: ProductCard2) = GlobalScope.launch {
        favRepo.deleteFavorite(Features().toFavoriteCache(favorite))
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
    fun updateCart(list: MutableList<Int>,checkOut : List<CheckOutItem>){
        cartRepository.updateCart(list,checkOut)
    }
    fun addCheckOut(index : Int , quantity : Int){
        cartRepository.addToCheckOut(index,quantity)
    }
}