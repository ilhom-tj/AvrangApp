package tj.colibri.avrang.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.Cart.Cart
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.products.ProductRepo

class CartViewModel(application: Application) : AndroidViewModel(application) {


    val cartRepo = CartRepository(application.applicationContext)

    val getCartItems = cartRepo.getCart()

    var totalPrice = cartRepo.totalPrice


    fun removeFromCart(cartItem: CartItem) = GlobalScope.launch {
        cartRepo.remoeFromCart(cartItem)
    }
}