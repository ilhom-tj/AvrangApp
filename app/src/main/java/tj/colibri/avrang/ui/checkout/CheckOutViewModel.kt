package tj.colibri.avrang.ui.checkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tj.colibri.avrang.network.repositories.checkout.CheckOutRepo
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.cities.CityRepo

class CheckOutViewModel(application: Application) : AndroidViewModel(application) {

    private val checkOutRepo = CheckOutRepo(application.applicationContext)
    private val cityRepo = CityRepo(application.applicationContext)
    private val cartRepo = CartRepository(application.applicationContext)

    val cities = cityRepo.getCities()
    var data = checkOutRepo.GetDetails()

}