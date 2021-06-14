package tj.colibri.avrang.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.cart.CartItemResponse
import tj.colibri.avrang.data.favorite.FavoriteCard
import tj.colibri.avrang.data.ApiData.product.ProductCard2
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Features {


    fun toChacheCartItem(cartItem: CartItemResponse): CartItem {
        return CartItem(
            cartItem.id,
            cartItem.slug,
            cartItem.sku,
            cartItem.name,
            1,
            cartItem.images[0],
            cartItem.price,
            cartItem.bonus,
            cartItem.in_stock
        )
    }

    fun toSliderItemFromString(list: List<String>): List<Sliders> {
        val sliderList = ArrayList<Sliders>()
        var slider: Sliders
        val index = 0
        list.forEach { str ->
            slider = Sliders(index, str, str, str)
            slider.id = index
            slider.image = str
            slider.url = str
            slider.mob_image = str
            Log.e("imgs2", slider.image)
            sliderList.add(slider)
        }
        return sliderList.toList()
    }

    fun toFavoriteCache(item: ProductCard2): FavoriteCard {
        return FavoriteCard(
            item.id,
            item.name,
            item.slug,
            item.sKU,
            item.productPrice,
            item.newPrice,
            item.images[0]
        )
    }

    fun toCurrentCategories(mass : ArrayList<String>) : String{
        val str = mass.toString()
        return str.replace("[","").replace("]","")
    }

    @SuppressLint("SimpleDateFormat")
    fun getNormalDate(date: Date) : String{
        return SimpleDateFormat("dd-MM-yyyy").format(date).toString().replace("-", ".")
    }
    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}