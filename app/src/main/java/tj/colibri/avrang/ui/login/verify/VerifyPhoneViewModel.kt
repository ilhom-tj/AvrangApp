package tj.colibri.avrang.ui.login.verify

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.ApiData.registration.ConfirmCode
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo

class VerifyPhoneViewModel(application: Application) : AndroidViewModel(application) {
    val repo  = RegistrationRepo(application.applicationContext)

    fun getVerificationSubmit(phone : String,code: Int) : LiveData<ConfirmCode>{
        return repo.getVerificationSubmit(phone,code.toString())
    }
}