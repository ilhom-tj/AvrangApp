package tj.colibri.avrang.network.repositories.cartRepo

import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Cart.*
import tj.colibri.avrang.data.ApiData.chekout.CheckOutDB
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import tj.colibri.avrang.data.cart.CartDB
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.cart.CartItemResponse
import tj.colibri.avrang.network.RetrofitInstance
import tj.colibri.avrang.utils.Features

class CartRepository(context: Context) {

    private val cartDao = CartDB.getInstance(context).cartDao
    private val cartIndexDao = CartIndexDB.getInstance(context).cartIndexDao
    private val checkOutDao = CheckOutDB.getInstance(context).checkOutDaoDao

    var cartIndexes = cartIndexDao.getCartIndexes()
    val api = RetrofitInstance(context).api()
    val totalPrice = cartDao.getTotalPrice()
    val totalBonuses = cartDao.getTotalBonus()

    val checkOutList = checkOutDao.getAllItems()
    fun getCart() = cartDao.getCartItems()


    fun updateCart(list: List<Int>,listCheckOutItem: List<CheckOutItem>) {
        val cartResponse = CartIndexResponse(list)

        api.updateCart(cartResponse).enqueue(object : Callback<UpdateCart> {
            override fun onResponse(call: Call<UpdateCart>, response: Response<UpdateCart>) {
                if (response.isSuccessful) {
                    if (listCheckOutItem.isNotEmpty()){
                        var indexs = 0
                        response.body()?.cartData?.forEach { items ->
                            items.quantity = listCheckOutItem.get(indexs).quantity
                            addToCart(items)
                            indexs++
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UpdateCart>, t: Throwable) {

            }

        })
    }

    fun updateItemQuantity(checkOutItem: CheckOutItem){
        cartDao.updateQuantity(checkOutItem.id,checkOutItem.quantity)
    }

    fun addToCheckOut(index : Int , quantity : Int) = GlobalScope.launch{
        val checkOutItem = CheckOutItem(index,quantity)
        checkOutDao.addCheckOutItem(checkOutItem)
    }

    fun addToCart(cartItem: CartItemResponse) = GlobalScope.launch {
        cartDao.addCartItem(Features().toChacheCartItem(cartItem))
    }

    fun addToCart(cartItem: CartItem) = GlobalScope.launch {
        cartDao.addCartItem(cartItem)
    }

    fun remoeFromCart(cartItem: CartItem) {
        cartDao.removeFromCart(cartItem)
        cartIndexDao.deleteIndex(cartItem.id)
        checkOutDao.deleteById(cartItem.id)
    }

    fun remoeFromAll() {
        cartDao.removeFromCartAll()
        cartIndexDao.deleteAllIndexes()
        checkOutDao.deleteAllIndexes()
    }

    fun addToCart(id: Int) = GlobalScope.launch {
        val index = CartIndex(id)
        cartIndexDao.addItemIndex(index)
    }


}