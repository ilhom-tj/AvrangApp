package tj.colibri.avrang.ui.login.password

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.ApiData.registration.RegistrationCallBack
import tj.colibri.avrang.data.user.User
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo
import tj.colibri.avrang.network.repositories.userRepo.UserRepository

class PasswordViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository(application)
    val regRepo = RegistrationRepo(application.applicationContext)
    fun setUser(user: User){
        userRepo.updateUser(user)
    }

    fun registrationUser(phone: String,password : String,password_confirm: String) : LiveData<RegistrationCallBack>{
        return regRepo.getRegistredUser(phone,password,password_confirm)
    }

}