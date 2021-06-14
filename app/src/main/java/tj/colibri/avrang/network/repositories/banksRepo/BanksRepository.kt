package tj.colibri.avrang.network.repositories.banksRepo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.models.Installment.InstallmentBanks
import tj.colibri.avrang.network.RetrofitInstance

class BanksRepository(context: Context){
    val api = RetrofitInstance(context).api()

    fun getBanks() : LiveData<InstallmentBanks>{
        val liveData = MutableLiveData<InstallmentBanks>()
        api.getBanks().enqueue(object : Callback<InstallmentBanks>{
            override fun onResponse(
                call: Call<InstallmentBanks>,
                response: Response<InstallmentBanks>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<InstallmentBanks>, t: Throwable) {

            }

        })
        return liveData
    }
}