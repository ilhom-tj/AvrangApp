package tj.colibri.avrang.ui.cart

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
class CartViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    val cartRepo = CartRepository(application.applicationContext)

    val indexes = cartRepo.checkOutList
    val getCartItems = cartRepo.getCart()

    var totalPrice = cartRepo.totalPrice
    var totalBonuses = cartRepo.totalBonuses

    fun updateCartItemQunatity(checkOutItem: CheckOutItem) = GlobalScope.launch{
        cartRepo.updateItemQuantity(checkOutItem)
    }

    fun removeFromCart(cartItem: CartItem) = GlobalScope.launch {
        cartRepo.remoeFromCart(cartItem)
    }

}