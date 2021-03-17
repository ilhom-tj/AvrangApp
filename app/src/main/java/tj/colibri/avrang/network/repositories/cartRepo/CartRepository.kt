package tj.colibri.avrang.network.repositories.cartRepo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Cart.Cart
import tj.colibri.avrang.data.cart.CartDB
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.network.RetrofitInstance

class CartRepository(context: Context){

    val cartDao = CartDB.getInstance(context).cartDao
    val api = RetrofitInstance(context).api()
    val totalPrice = cartDao.getTotalPrice()
    fun getCart() = cartDao.getCartItems()

    fun addToCart(cartItem: CartItem) = GlobalScope.launch{
        cartDao.addCartItem(cartItem)
    }
    fun remoeFromCart(cartItem: CartItem){
        cartDao.removeFromCart(cartItem)
    }

    fun addToCart(id: Int,quantity : Int) : LiveData<Cart> {
        var liveData = MutableLiveData<Cart>()
        api.addToCart(id,quantity).enqueue(object : Callback<Cart> {
            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                    response.body()!!.cartData.forEach { cartItem: CartItem ->
                        addToCart(cartItem)
                    }
                }
            }

            override fun onFailure(call: Call<Cart>, t: Throwable) {

            }

        })
        return liveData
    }
}