package tj.colibri.avrang.network.repositories.homeRepo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.models.Home.HomeResponse
import tj.colibri.avrang.network.RetrofitInstance
class HomeRepo(val context: Context) {
    val api = RetrofitInstance(context).api()

    fun getHome(): LiveData<HomeResponse> {
        val home = MutableLiveData<HomeResponse>()
        api.getHome().enqueue(object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.isSuccessful) {
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

}