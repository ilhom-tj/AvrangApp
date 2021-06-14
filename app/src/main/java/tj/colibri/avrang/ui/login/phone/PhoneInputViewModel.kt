package tj.colibri.avrang.ui.login.phone

import android.app.Application
import androidx.lifecycle.*
import tj.colibri.avrang.models.Registration.ConfirmCode
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo

class PhoneInputViewModel(application: Application) : AndroidViewModel(application) {


    val regRepo = RegistrationRepo(application.applicationContext)

    fun getVerificationCode(phone : String) : LiveData<ConfirmCode> {
        return regRepo.getVerificationCode(phone)
    }
}