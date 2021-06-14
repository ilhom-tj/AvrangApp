package tj.colibri.avrang.network.repositories.cartRepo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Cart.*
import tj.colibri.avrang.data.ApiData.Installment.PayClass
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


    fun take_loan(pay: PayClass): LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        api.take_loan(pay).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                {
                    Log.e("respo ", response.code().toString())
                    liveData.value = true
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })
        return liveData
    }

    fun updateCart(list: List<Int>, listCheckOutItem: List<CheckOutItem>) {
        val cartResponse = CartIndexResponse(list)

        api.updateCart(cartResponse).enqueue(object : Callback<UpdateCart> {
            override fun onResponse(call: Call<UpdateCart>, response: Response<UpdateCart>) {
                if (response.isSuccessful) {
                    if (listCheckOutItem.isNotEmpty()) {
                        Log.e("BUG TRACER", listCheckOutItem.toTypedArray().contentToString())

                        response.body()?.cartData?.forEachIndexed { i, items ->
                            try {
                                items.quantity = listCheckOutItem[i].quantity
                                addToCart(items)
                            } catch (ex: Exception) {
                                Log.e("FIX", ex.message.toString())
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UpdateCart>, t: Throwable) {

            }

        })
    }

    fun updateItemQuantity(checkOutItem: CheckOutItem) {
        cartDao.updateQuantity(checkOutItem.id, checkOutItem.quantity)
    }


    fun addToCheckOut(index: Int, quantity: Int) = GlobalScope.launch {
        val checkOutItem = CheckOutItem(index, quantity)
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

    fun remoeFromAll() = GlobalScope.launch {
        cartDao.removeFromCartAll()
        cartIndexDao.deleteAllIndexes()
        checkOutDao.deleteAllIndexes()
    }

    fun addToCart(id: Int) = GlobalScope.launch {
        val index = CartIndex(id)
        cartIndexDao.addItemIndex(index)
    }


}