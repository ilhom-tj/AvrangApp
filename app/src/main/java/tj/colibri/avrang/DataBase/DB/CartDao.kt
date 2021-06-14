package tj.colibri.avrang.DataBase.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import tj.colibri.avrang.data.Cart.CartItem

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_table")
    fun getCartItems() : LiveData<MutableList<CartItem>>


    @Query("SELECT SUM(cart_unit_price * cart_quantity) FROM cart_table ")
    fun getTotalPrice() : LiveData<Double>

    @Query("SELECT SUM(cart_bonus) FROM cart_table")
    fun getTotalBonus() : LiveData<Int>

    @Query("UPDATE cart_table SET cart_quantity=:quantity WHERE id=:id")
    fun updateQuantity(id : Int,quantity : Int)

    @Delete
    fun removeFromCart(cartItem: CartItem)

    @Query("DELETE FROM cart_table")
    fun removeFromCartAll()
    
}