package tj.colibri.avrang.ui.login.verify

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tj.colibri.avrang.models.Registration.ConfirmCode
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo

class VerifyPhoneViewModel(application: Application) : AndroidViewModel(application) {
    val repo  = RegistrationRepo(application.applicationContext)

    fun getVerificationSubmit(phone : String,code: Int) : LiveData<ConfirmCode>{
        return repo.getVerificationSubmit(phone,code.toString())
    }
    fun getVerificationCode(phone : String) : LiveData<ConfirmCode> {
        return repo.getVerificationCode(phone)
    }
}