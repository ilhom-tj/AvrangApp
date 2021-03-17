package tj.colibri.avrang.utils

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo
import tj.colibri.avrang.network.repositories.userRepo.UserRepository

class RefresToken(context: Application){

    val context = context
    val regRepository = RegistrationRepo(context)

    fun RefresToken() : LiveData<String>{
        val token = MutableLiveData<String>()

        if (SessionManager(context).getToken() != "error"){
            val user = UserRepository(context).getUser.value
         //   regRepository.login(user.phone,SessionManager(context).getPassword()).observe(,)
        }
        return token
    }
}