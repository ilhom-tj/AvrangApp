package tj.colibri.avrang.network.repositories.products

import android.content.Context
import android.content.LocusId
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Cart.Cart
import tj.colibri.avrang.data.ApiData.product.ProductInformation
import tj.colibri.avrang.network.RetrofitInstance

class ProductRepo(context: Context) {

    val context = context
    val api = RetrofitInstance(context).api()
    //val productDao = ProductDB.getInstance(context).productDao

    fun getProduct(slug: String) : LiveData<ProductInformation>{
        val liveData = MutableLiveData<ProductInformation>()
        api.getProduct(slug).enqueue(object : Callback<ProductInformation>{
            override fun onResponse(call: Call<ProductInformation>, response: Response<ProductInformation>) {
                if (response.isSuccessful){
                    Log.e("error",response.body().toString())
                    liveData.value = response.body()

                }
            }

            override fun onFailure(call: Call<ProductInformation>, t: Throwable) {

            }

        })
        return liveData
    }


}