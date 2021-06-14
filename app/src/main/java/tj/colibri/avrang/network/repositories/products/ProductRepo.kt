package tj.colibri.avrang.network.repositories.products

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.network.RetrofitInstance

class ProductRepo(val context: Context) {

    val api = RetrofitInstance(context).api()
    
    fun getProduct(slug: String) : LiveData<ProductInfortmation2>{
        val liveData = MutableLiveData<ProductInfortmation2>()
        api.getProduct(slug).enqueue(object : Callback<ProductInfortmation2>{
            override fun onResponse(call: Call<ProductInfortmation2>, response: Response<ProductInfortmation2>) {
                Log.e("PRODUCT_RESPONSE", response.code().toString())
                if (response.isSuccessful){
                    Log.e("error",response.body().toString())
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ProductInfortmation2>, t: Throwable) {
                Log.e("ERRRRR",t.message.toString())
            }

        })
        return liveData
    }


}