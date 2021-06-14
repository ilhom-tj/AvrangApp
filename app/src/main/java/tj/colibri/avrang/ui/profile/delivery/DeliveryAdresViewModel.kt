package tj.colibri.avrang.ui.profile.delivery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tj.colibri.avrang.network.repositories.userRepo.UserRepository

class DeliveryAdresViewModel(application: Application) : AndroidViewModel(application){
    private val userRepository = UserRepository(application)

    fun updateAdress(main_adres : String,additional : String) : LiveData<Boolean>{
        return userRepository.updateAdresses(main_adres,additional)
    }
}