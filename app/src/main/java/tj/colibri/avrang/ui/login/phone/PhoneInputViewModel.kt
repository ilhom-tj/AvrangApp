package tj.colibri.avrang.ui.login.phone

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.runBlocking
import tj.colibri.avrang.data.ApiData.registration.ConfirmCode
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo

class PhoneInputViewModel(application: Application) : AndroidViewModel(application) {


    val regRepo = RegistrationRepo(application.applicationContext)

    fun getVerificationCode(phone : String) : LiveData<ConfirmCode> {
        return regRepo.getVerificationCode(phone)
    }
}