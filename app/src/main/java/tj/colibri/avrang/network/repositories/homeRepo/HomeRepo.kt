package tj.colibri.avrang.network.repositories.homeRepo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.home.HomeResponse
import tj.colibri.avrang.data.ApiData.product.room.ProductDao
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.RetrofitInstance
import tj.colibri.avrang.utils.Features

class HomeRepo(context: Context) {
    val context = context
    val api = RetrofitInstance(context).api()

  //  val productDao = ProductDB.getInstance(context).productDao

//    val getNewProducts = productDao.getNewProducts()
//
//    val getPopularProducts = productDao.getPopularProducts()
//
//    val getRecommendedProducts = productDao.getRecommendedProducts()
//
//    val getMaxDiscountProducts = productDao.getMaxDiscountProducts()

    fun getHome(): LiveData<HomeResponse> {
        var home = MutableLiveData<HomeResponse>()
        api.getHome().enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.isSuccessful) {
//                    home.value = response.body()
//                    response.body()!!.newProducts.forEach { prd ->
//                        var product: ProductCard2 = prd
//                        product.type = 1;
//                    //    addToProductCache(product)
//                    }
//                    response.body()!!.popular.forEach { prd ->
//                        var product: ProductCard2 = prd
//                        product.type = 2;
//                     //   addToProductCache(product)
//                    }
//                    response.body()!!.recomended.forEach { prd ->
//                        var product: ProductCard2 = prd
//                        product.type = 3;
//                //        addToProductCache(product)
//                    }
//                    response.body()!!.maxDiscounts.forEach { prd ->
//                        var product: ProductCard2 = prd
//                        product.type = 4;
//                //        addToProductCache(product)
//                    }
                    home.value = response.body()
                }
                if (response.code() == 504) {
                    Toast.makeText(context, "Не удаётся подключится к серверу", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.e("HomeRepo", t.message.toString())
            }


        })
        return home
    }

//    fun addToProductCache(product : ProductCard2) = GlobalScope.launch{
//        productDao.addProduct(Features().ProductApiToCacheType(product))
//    }

}