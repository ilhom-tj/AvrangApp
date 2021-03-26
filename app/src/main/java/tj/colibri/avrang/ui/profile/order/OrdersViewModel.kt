package tj.colibri.avrang.ui.profile.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.order.MyOrdersRequest
import tj.colibri.avrang.network.repositories.userRepo.UserRepository

class OrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo = UserRepository(application.applicationContext)

    fun getMyOrders() : LiveData<MyOrdersRequest>{
        return userRepo.getMyOrders()
    }
}