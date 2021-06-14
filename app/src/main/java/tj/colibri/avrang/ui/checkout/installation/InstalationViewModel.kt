package tj.colibri.avrang.ui.checkout.installation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tj.colibri.avrang.models.Installment.InstallmentBanks
import tj.colibri.avrang.models.Installment.PayClass
import tj.colibri.avrang.data.User.User
import tj.colibri.avrang.network.repositories.banksRepo.BanksRepository
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.userRepo.UserRepository

class InstalationViewModel(application: Application) : AndroidViewModel(application) {

    private val banksRepository = BanksRepository(application.applicationContext)
    private val userRepo = UserRepository(application.applicationContext)
    val cartRepository = CartRepository(application.applicationContext)
    val totalPrice = cartRepository.totalPrice

    fun take_loan(pay : PayClass) : LiveData<Boolean>{
        return cartRepository.take_loan(pay)
    }
    fun getBanks() : LiveData<InstallmentBanks>{
        return banksRepository.getBanks()
    }

    fun getUserInfo() : LiveData<User>{
        return userRepo.getProfile()
    }

    fun getCartItems() = cartRepository.checkOutList

}