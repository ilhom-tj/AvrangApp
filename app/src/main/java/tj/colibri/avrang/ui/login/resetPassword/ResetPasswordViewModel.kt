package tj.colibri.avrang.ui.login.resetPassword

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.ApiData.registration.ConfirmCode
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo

class ResetPasswordViewModel(application: Application) : AndroidViewModel(application) {
    val regApi = RegistrationRepo(application.applicationContext)

    fun resetPassword(phone: String): LiveData<ConfirmCode> {
        return regApi.resetPassword(phone)
    }

    fun resetPassword(phone: String, code: String): LiveData<ConfirmCode> {
        return regApi.resetPassword(phone, code)
    }

    fun changePassword(phone: String, password: String, password_confirm: String) : LiveData<ConfirmCode> {
        return regApi.changePassword(
            phone,
            password,
            password_confirm)
    }
}