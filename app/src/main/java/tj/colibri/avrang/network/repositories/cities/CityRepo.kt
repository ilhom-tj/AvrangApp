package tj.colibri.avrang.network.repositories.cities

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.models.Cities.CitiesResponse
import tj.colibri.avrang.data.Cities.Cities
import tj.colibri.avrang.network.RetrofitInstance

class CityRepo(val context: Context) {

    val api = RetrofitInstance(context).api()

    fun getCities() : LiveData<List<Cities>>{
        val cities = MutableLiveData<List<Cities>>()
        api.getCitiesList().enqueue(object : Callback<CitiesResponse>{
            override fun onResponse(
                call: Call<CitiesResponse>,
                response: Response<CitiesResponse>
            ) {
                if (response.isSuccessful){
                    cities.value = response.body()!!.cities
                }
                if (response.code() == 504){
                    Toast.makeText(context,"Не удаётся подключится к серверу", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CitiesResponse>, t: Throwable) {
                Log.e("CITY",t.message.toString())
            }

        })
        return cities
    }
}