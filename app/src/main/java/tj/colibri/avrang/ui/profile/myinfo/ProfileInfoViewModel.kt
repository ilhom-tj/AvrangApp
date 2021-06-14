package tj.colibri.avrang.ui.profile.myinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import tj.colibri.avrang.data.cities.Cities
import tj.colibri.avrang.data.user.User
import tj.colibri.avrang.network.repositories.cities.CityRepo
import tj.colibri.avrang.network.repositories.userRepo.UserRepository
import java.io.InputStream

class ProfileInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)

    val user = userRepository.getProfile()
    private val cityRepo = CityRepo(application.applicationContext)

    fun updateUserPersonalInfo(
        name: String,
        birthdate: String,
        phone: String,
        email: String,
        additional_phone: String,
        city_id: Int,
        gender: Int,
        main_address: String,
        additional_address: String
    ): LiveData<User> {
        return userRepository.updatePersonalInfo(
            name,
            birthdate,
            phone,
            email,
            additional_phone,
            city_id,
            gender,
            main_address,
            additional_address
        )
    }

    fun updateProfileImage(inputStream: InputStream): LiveData<Boolean> {
        val part = MultipartBody.Part.createFormData(
            "image", "userAvatar", RequestBody.create(
                MediaType.parse("image/*"),
                inputStream.readBytes()
            )
        )
        return userRepository.updateUserImage(part)
    }


    fun getCities(): LiveData<List<Cities>> {

        return cityRepo.getCities()
    }
}