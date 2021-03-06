package tj.colibri.avrang.network.repositories.checkout

import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResquest
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutBankResponse
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResponse
import tj.colibri.avrang.data.OrderDetails
import tj.colibri.avrang.network.RetrofitInstance

class CheckOutRepo(context : Context) {
    val api = RetrofitInstance(context).api()


    fun GetDetails() : LiveData<OrderDetails>{
        val liveData = MutableLiveData<OrderDetails>()
        api.orderDetails().enqueue(object : Callback<OrderDetails>{
            override fun onResponse(call: Call<OrderDetails>, response: Response<OrderDetails>) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<OrderDetails>, t: Throwable) {

            }

        })
        return liveData
    }

    fun CheckOutCart(checkOutResquest: CheckOutResquest) : LiveData<CheckOutResponse>{
        val liveData = MutableLiveData<CheckOutResponse>()
        api.checkOut(checkOutResquest).enqueue(object : Callback<CheckOutResponse>{
            override fun onResponse(
                call: Call<CheckOutResponse>,
                response: Response<CheckOutResponse>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CheckOutResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return liveData
    }

    fun CheckOutCartBank(checkOutResquest: CheckOutResquest) : LiveData<CheckOutBankResponse>{
        val liveData = MutableLiveData<CheckOutBankResponse>()
        api.checkOutBank(checkOutResquest).enqueue(object : Callback<CheckOutBankResponse>{
            override fun onResponse(
                call: Call<CheckOutBankResponse>,
                response: Response<CheckOutBankResponse>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CheckOutBankResponse>, t: Throwable) {

            }

        })
        return liveData
    }
}