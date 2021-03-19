package tj.colibri.avrang.network.repositories.userRepo

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.profile.ProfileResponse
import tj.colibri.avrang.data.favorite.FavoriteDB
import tj.colibri.avrang.data.user.User
import tj.colibri.avrang.data.user.UserDao
import tj.colibri.avrang.data.user.UserDataBase
import tj.colibri.avrang.network.RetrofitInstance
import java.util.*
import javax.security.auth.callback.Callback

class UserRepository(context: Context ) {

    val context = context
    val api = RetrofitInstance(context.applicationContext).api()
    private var userDao: UserDao = UserDataBase.getInstance(context.applicationContext).userDao

    val getUser = userDao.getUser()

    fun updateUser(user: User) = GlobalScope.launch{
        userDao.addUser(user)
    }
    fun updatePersonalInfo(
        name : String,
        birthdate : String,
        phone : String,
        email : String,
        addional_phone : String,
        city_id : Int,
        gender : Int,
        addresses : String) : LiveData<User>{
        var liveData = MutableLiveData<User>()

        Log.e("additional1",addional_phone)
        api.updatePersonalInfo(name,birthdate,phone,email,addional_phone,city_id,gender,addresses).enqueue(
            object : retrofit2.Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        liveData.value = response.body()
                        updateUser(response.body()!!)
                    }
                    if (response.code() == 504){
                        Toast.makeText(context,"Не удаётся подключится к серверу", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("UPDATE_PROFILE",response.code().toString() + response.errorBody().toString())
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("Error",t.message.toString())
                }
            })
        return liveData

    }
    fun clean() = GlobalScope.launch {
        userDao.cleanUsers()
    }
    fun getProfile() : LiveData<User>{
        val liveData = MutableLiveData<User>()
        api.getProfileInfo().enqueue(object : retrofit2.Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful){
                    Log.e("phone",response.body().toString())
                    liveData.value = response.body()!!.user
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

            }

        })
        return liveData
    }
}