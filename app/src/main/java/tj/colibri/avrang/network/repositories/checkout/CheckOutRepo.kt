package tj.colibri.avrang.network.repositories.checkout

import tj.colibri.avrang.data.ApiData.chekout.forRequest.CheckOutResquest
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.chekout.forRequest.CheckOutResponse
import tj.colibri.avrang.data.ApiData.orderDetails.OrderDetails
import tj.colibri.avrang.network.RetrofitInstance

class CheckOutRepo(context : Context) {
    val api = RetrofitInstance(context).api()


    fun GetDetails() : LiveData<OrderDetails>{
        var liveData = MutableLiveData<OrderDetails>()
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
        var liveData = MutableLiveData<CheckOutResponse>()
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
}