package tj.colibri.avrang.utils

import android.annotation.SuppressLint
import android.util.Log
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.cart.CartItemResponse
import tj.colibri.avrang.data.favorite.FavoriteCard
import tj.colibri.avrang.data.mock.ProductCard2
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Features {

    fun toChacheCartItem(cartItem: CartItemResponse): CartItem {
        val cartItem1 = CartItem(
            cartItem.id,
            cartItem.slug,
            cartItem.sku,
            cartItem.name,
            1,
            cartItem.images.get(0),
            cartItem.price,
            cartItem.bonus,
            cartItem.in_stock
        )
        return cartItem1
    }

    fun toSliderItemFromString(list: List<String>): List<Sliders> {
        var sliderList = ArrayList<Sliders>()
        var slider = Sliders(0, "", "", "")
        val index = 0;
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

    @SuppressLint("SimpleDateFormat")
    fun getNormalDate(date : Date) : String{
        return SimpleDateFormat("dd-MM-yyyy").format(date).toString().replace("-",".")
    }
}