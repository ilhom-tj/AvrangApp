package tj.colibri.avrang.utils

import android.transition.Slide
import android.util.Log
import tj.colibri.avrang.data.ApiData.home.Labels
import tj.colibri.avrang.data.ApiData.product.Product
import tj.colibri.avrang.data.ApiData.product.Rating
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.cart.CartItemResponse
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.data.slider.SliderItem

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
//    fun cleanURL(imglist : List<String>) : String{
//        val spl = 34.toChar();
//        val readyimg = imglist[0].replace("\\","")
//            .replace("[","")
//            .replace("]","")
//            .replace(spl.toString(),"")
//        return Const.image_url + readyimg;
//    }
//    fun ProductApiToCacheType(product : ProductCard2) : ProductCache{
//        val productChache = ProductCache(
//            product.id,
//            product.name,
//            product.sKU,
//            product.productPrice,
//            product.isFavorite,
//            product.rating.rating,
//            product.rating.quantity,
//            product.rating.is_rated,
//            product.labels.is_hit,
//            product.labels.is_promotion,
//            product.labels.has_gift,
//            product.slug,
//            product.newPrice,
//            product.images,
//            product.quantity,
//            product.bonus,
//            product.type
//        )
//        return productChache
//    }

//    fun ProductCacheToRating(productCache: ProductCache) : tj.colibri.avrang.data.ApiData.product.ProductInfo.Rating{
//        val rating = tj.colibri.avrang.data.ApiData.product.ProductInfo.Rating(productCache.rating,productCache.rating_quantity,productCache.is_rated)
//        return rating
//    }
//
//    fun ProductCacheToLabels(productCache: ProductCache) : Labels{
//        val labels = Labels(productCache.is_hit,productCache.is_promotion,productCache.has_gift)
//        return labels
//    }
}