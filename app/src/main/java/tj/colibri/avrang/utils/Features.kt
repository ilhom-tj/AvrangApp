package tj.colibri.avrang.utils

import tj.colibri.avrang.data.ApiData.home.Labels
import tj.colibri.avrang.data.ApiData.product.Product
import tj.colibri.avrang.data.ApiData.product.Rating
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.mock.ProductCard2

class Features {
    fun cleanURL(imglist : List<String>) : String{
        val spl = 34.toChar();
        val readyimg = imglist[0].replace("\\","")
            .replace("[","")
            .replace("]","")
            .replace(spl.toString(),"")
        return Const.image_url + readyimg;
    }
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

//    fun ProductCacheToRating(productCache: ProductCache) : Rating{
//        val rating = Rating(productCache.rating,productCache.rating_quantity,productCache.is_rated)
//        return rating
//    }
//
//    fun ProductCacheToLabels(productCache: ProductCache) : Labels{
//        val labels = Labels(productCache.is_hit,productCache.is_promotion,productCache.has_gift)
//        return labels
//    }
}