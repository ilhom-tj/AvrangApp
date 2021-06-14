package tj.colibri.avrang.network.repositories.registrationRepo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.registration.ConfirmCode
import tj.colibri.avrang.data.ApiData.registration.RegistrationCallBack
import tj.colibri.avrang.network.RetrofitInstance
import tj.colibri.avrang.network.repositories.userRepo.UserRepository
import tj.colibri.avrang.utils.SessionManager

class RegistrationRepo(val context: Context) {

    val api = RetrofitInstance(context).api()
    val errorMessage = MutableLiveData<String>()
    val userRepository = UserRepository(context)
    val sessionManager = SessionManager(context)

    fun getVerificationCode(phone : String) : LiveData<ConfirmCode>   {
        val liveData = MutableLiveData<ConfirmCode>()
        api.firstStepRegister(phone).enqueue(object : Callback<ConfirmCode>{
            override fun onResponse(call: Call<ConfirmCode>, response: Response<ConfirmCode>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
                if (response.code() == 504){
                    Toast.makeText(context,"Не удаётся подключится к серверу",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ConfirmCode>, t: Throwable) {
                println(t.message)
                errorMessage.value = t.message
            }
        })

        return liveData

    }

    fun getVerificationSubmit(phone: String,code: String) : LiveData<ConfirmCode>{
        val confirmCode = MutableLiveData<ConfirmCode>()
        api.secondtStepRegister(phone,code).enqueue(object : Callback<ConfirmCode>{
            override fun onResponse(call: Call<ConfirmCode>, response: Response<ConfirmCode>) {
                if (response.isSuccessful){
                    confirmCode.value = response.body()
                }
                if (response.code() == 504){
                    Toast.makeText(context,"Не удаётся подключится к серверу",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ConfirmCode>, t: Throwable) {
                println(t.message)
                errorMessage.value = t.message
            }

        })
        return confirmCode
    }

    fun getRegistredUser(phone: String,password : String,password_verify: String) : LiveData<RegistrationCallBack> {
        val registeredUser = MutableLiveData<RegistrationCallBack>()
        api.registration(password,password_verify,phone).enqueue(object : Callback<RegistrationCallBack>{
            override fun onResponse(
                call: Call<RegistrationCallBack>,
                response: Response<RegistrationCallBack>
            ) {
                if (response.isSuccessful){
                    Log.e("TOOOLKK",response.body()!!.access_token)
                    registeredUser.value = response.body()
                    userRepository.clean()
                    sessionManager.deleteAll()
                    userRepository.updateUser(registeredUser.value!!.user)
                    sessionManager.setToken(registeredUser.value!!.access_token)
                    sessionManager.setPhone(registeredUser.value!!.user.phone.toString())
                    sessionManager.setPassword(password)
                }
                if (response.code() == 504){
                    Toast.makeText(context,"Не удаётся подключится к серверу",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegistrationCallBack>, t: Throwable) {
                print(t.message)
                Log.e("ERRROOR","THIS IS ERROR")
                errorMessage.value = t.message
            }
        })
        return registeredUser
    }

    fun login(phone: String,password: String) : LiveData<RegistrationCallBack> {
        val liveData = MutableLiveData<RegistrationCallBack>()
        api.login(phone,password).enqueue(object : Callback<RegistrationCallBack>{
            override fun onResponse(
                call: Call<RegistrationCallBack>,
                response: Response<RegistrationCallBack>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                    userRepository.clean()
                    userRepository.updateUser(liveData.value!!.user)
                    sessionManager.deleteAll()
                    sessionManager.setToken(liveData.value!!.access_token)
                    sessionManager.setPhone(liveData.value!!.user.phone.toString())
                    sessionManager.setPassword(password)
                }
                if (response.code() == 422 || response.code() == 401){
                    Toast.makeText(context,"Неправильный номер или пароль",Toast.LENGTH_SHORT).show()
                }
                if (response.code() == 504){
                    Toast.makeText(context,"Не удаётся подключится к серверу",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegistrationCallBack>, t: Throwable) {
                errorMessage.value = t.message
            }

        })
        return liveData
    }

    fun resetPassword(phone: String) : LiveData<ConfirmCode>{
        val liveData =  MutableLiveData<ConfirmCode>()
        api.resetPassword(phone).enqueue(object : Callback<ConfirmCode>{
            override fun onResponse(call: Call<ConfirmCode>, response: Response<ConfirmCode>) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ConfirmCode>, t: Throwable) {

            }

        })
        return liveData
    }
    fun resetPassword(phone: String ,code: String) : LiveData<ConfirmCode>{
        val liveData =  MutableLiveData<ConfirmCode>()
        api.resetPassword(phone,code).enqueue(object : Callback<ConfirmCode>{
            override fun onResponse(call: Call<ConfirmCode>, response: Response<ConfirmCode>) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ConfirmCode>, t: Throwable) {

            }

        })
        return liveData
    }
    fun changePassword(phone: String,password: String,password_confirm : String) : LiveData<ConfirmCode>{
        val liveData =  MutableLiveData<ConfirmCode>()
        api.changePassword(phone,password,password_confirm).enqueue(object : Callback<ConfirmCode>{
            override fun onResponse(call: Call<ConfirmCode>, response: Response<ConfirmCode>) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ConfirmCode>, t: Throwable) {
            }

        })
        return liveData
    }
}

