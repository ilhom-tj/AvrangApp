package tj.colibri.avrang.ui.checkout.chekoutprepare

import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResquest
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutBankResponse
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResponse
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.checkout.CheckOutRepo

class CheckOutPrepareViewModel(application: Application) : AndroidViewModel(application) {
    private val checkOutRepo = CheckOutRepo(application.applicationContext)
    val cartRepo = CartRepository(application.applicationContext)

    var checkOutResquest = MutableLiveData<CheckOutResquest>()

    fun checkOut(checkOutResquest: CheckOutResquest) : LiveData<CheckOutResponse>{
        return checkOutRepo.CheckOutCart(checkOutResquest)
    }

    fun clearCart() = GlobalScope.launch{
        cartRepo.remoeFromAll()
    }

    fun checkOutBank(checkOutResquest: CheckOutResquest) : LiveData<CheckOutBankResponse>{
        return checkOutRepo.CheckOutCartBank(checkOutResquest)
    }
}