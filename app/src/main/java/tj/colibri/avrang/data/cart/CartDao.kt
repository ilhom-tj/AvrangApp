package tj.colibri.avrang.data.cart

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_table")
    fun getCartItems() : LiveData<MutableList<CartItem>>


    @Query("SELECT SUM(cart_unit_price * cart_quantity) FROM cart_table ")
    fun getTotalPrice() : LiveData<Double>

    @Delete
    fun removeFromCart(cartItem: CartItem )
    
}