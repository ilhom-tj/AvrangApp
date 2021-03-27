package tj.colibri.avrang.network.repositories.catalogRepo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Category.CategoryProductRequest
import tj.colibri.avrang.data.ApiData.Category.CategoryResponse
import tj.colibri.avrang.network.RetrofitInstance

class CatalogRepo(context: Context) {
    private val api = RetrofitInstance(context).api()

    fun getCatalogs(): LiveData<CategoryResponse> {
        var liveData = MutableLiveData<CategoryResponse>()

        api.getCategories().enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

            }

        })
        return liveData
    }

    fun getCategoryProductsBySlug(slug: String , page : Int): LiveData<CategoryProductRequest> {
        val liveData = MutableLiveData<CategoryProductRequest>()
        api.getCategoryProducts(slug,page).enqueue(object : Callback<CategoryProductRequest> {
            override fun onResponse(
                call: Call<CategoryProductRequest>,
                response: Response<CategoryProductRequest>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CategoryProductRequest>, t: Throwable) {

            }
        })
        return liveData
    }
    fun getCategoryProductsBySlugIndex(slug: String , page : Int): LiveData<CategoryProductRequest> {
        val liveData = MutableLiveData<CategoryProductRequest>()
        api.getCategoryProducts(slug,page).enqueue(object : Callback<CategoryProductRequest> {
            override fun onResponse(
                call: Call<CategoryProductRequest>,
                response: Response<CategoryProductRequest>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CategoryProductRequest>, t: Throwable) {

            }
        })
        return liveData
    }

}