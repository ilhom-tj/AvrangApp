package tj.colibri.avrang.ui.login.signIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tj.colibri.avrang.models.Registration.RegistrationCallBack
import tj.colibri.avrang.data.User.User
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo
import tj.colibri.avrang.network.repositories.userRepo.UserRepository

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    val userRepository  = UserRepository(application)
    val regRepo = RegistrationRepo(application.applicationContext)

    val user = userRepository.getUser

    fun checkIfUserExists() : Boolean{
        return user != null
    }

    fun setUser(user: User){
        userRepository.updateUser(user)
    }

    fun login(phone : String,password : String) : LiveData<RegistrationCallBack>{
        return regRepo.login(phone,password)
    }
}