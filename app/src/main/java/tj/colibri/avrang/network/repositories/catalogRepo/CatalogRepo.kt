package tj.colibri.avrang.network.repositories.catalogRepo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Category.CategoryResponse
import tj.colibri.avrang.network.RetrofitInstance

class CatalogRepo(context : Context) {
    val api  = RetrofitInstance(context).api()

    fun getCatalogs() : LiveData<CategoryResponse>{
        var liveData = MutableLiveData<CategoryResponse>()

        api.getCategories().enqueue(object  : Callback<CategoryResponse>{
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

            }

        })
        return liveData
    }


}