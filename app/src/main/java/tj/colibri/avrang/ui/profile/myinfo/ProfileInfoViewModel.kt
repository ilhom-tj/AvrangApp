package tj.colibri.avrang.ui.profile.myinfo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.cities.Cities
import tj.colibri.avrang.data.user.User
import tj.colibri.avrang.network.repositories.cities.CityRepo
import tj.colibri.avrang.network.repositories.userRepo.UserRepository
import java.util.*

class ProfileInfoViewModel(application: Application) : AndroidViewModel(application) {
    val userRepository = UserRepository(application)

    val user = userRepository.getProfile()
    val cityRepo = CityRepo(application.applicationContext)

    fun updateUserPersonalInfo(
        name : String,
        birthdate : String,
        phone : String,
        email : String,
        additional_phone : String,
        city_id : Int,
        gender : Int,
        main_address: String,
        additional_address : String) : LiveData<User>{
        return userRepository.updatePersonalInfo(name,birthdate,phone,email,additional_phone,city_id,gender,main_address,additional_address)
    }

    fun getCities() : LiveData<List<Cities>>{

        return cityRepo.getCities()
    }
}