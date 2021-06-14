package tj.colibri.avrang.network.repositories.userRepo

import tj.colibri.avrang.data.user.User
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.profile.Image.ImageResponse
import tj.colibri.avrang.data.ApiData.profile.ProfileResponse
import tj.colibri.avrang.data.order.MyOrdersRequest
import tj.colibri.avrang.data.user.UserDao
import tj.colibri.avrang.data.user.UserDataBase
import tj.colibri.avrang.network.RetrofitInstance

class UserRepository(val context: Context) {

    val api = RetrofitInstance(context.applicationContext).api()
    private var userDao: UserDao = UserDataBase.getInstance(context.applicationContext).userDao

    val getUser = userDao.getUser()

    fun updateUser(user: User) = GlobalScope.launch {
        userDao.addUser(user)
    }
    fun updateUserImage(image : MultipartBody.Part) :LiveData<Boolean>{
        val liveData = MutableLiveData<Boolean>()
        api.updateProfileImage(image).enqueue(object : Callback<ImageResponse>{
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                liveData.value = response.isSuccessful
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {

            }

        })
        return liveData
    }

    fun updatePersonalInfo(
        name: String,
        birthdate: String,
        phone: String,
        email: String,
        additional_phone: String,
        city_id: Int,
        gender: Int,
        main_address: String,
        additional_adress: String
    ): LiveData<User> {
        val liveData = MutableLiveData<User>()
        api.updatePersonalInfo(
            name,
            birthdate,
            phone,
            email,
            additional_phone,
            city_id,
            gender,
            main_address,
            additional_adress
        ).enqueue(
            object : retrofit2.Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        liveData.value = response.body()
                        updateUser(response.body()!!)
                    }
                    if (response.code() == 504) {
                        Toast.makeText(context,"Не удаётся подключится к серверу", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("UPDATE_PROFILE",response.code().toString() + response.errorBody().toString())
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
        return liveData

    }

    fun clean() = GlobalScope.launch {
        userDao.cleanUsers()
    }

    fun getProfile(): LiveData<User> {
        val liveData = MutableLiveData<User>()
        api.getProfileInfo().enqueue(object : retrofit2.Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("phone", response.body().toString())
                    liveData.value = response.body()!!.user
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

            }

        })
        return liveData
    }

    fun getMyOrders() : LiveData<MyOrdersRequest>{
        val livedata = MutableLiveData<MyOrdersRequest>()
        api.getMyOrders().enqueue(object : retrofit2.Callback<MyOrdersRequest>{
            override fun onResponse(
                call: Call<MyOrdersRequest>,
                response: Response<MyOrdersRequest>
            ) {
                if (response.isSuccessful){
                    livedata.value = response.body()
                }
            }

            override fun onFailure(call: Call<MyOrdersRequest>, t: Throwable) {
                Log.e("MyOrders",t.message.toString())
            }

        })
        return livedata
    }

    fun updateAdresses(main_address: String , additional_adress: String) : LiveData<Boolean>{
        val liveData = MutableLiveData<Boolean>()
        api.updateUserAdress(main_address,additional_adress).enqueue(object  : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                liveData.value = response.isSuccessful
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })
        return liveData
    }
}