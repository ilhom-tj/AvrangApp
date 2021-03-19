package tj.colibri.avrang.ui.checkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.ApiData.orderDetails.OrderDetails
import tj.colibri.avrang.network.repositories.CheckOutRepo
import tj.colibri.avrang.network.repositories.cities.CityRepo

class CheckOutViewModel(application: Application) : AndroidViewModel(application) {

    private val checkOutRepo = CheckOutRepo(application.applicationContext)
    private val cityRepo = CityRepo(application.applicationContext)

    val cities = cityRepo.getCities()
    var data = checkOutRepo.GetDetails()


}