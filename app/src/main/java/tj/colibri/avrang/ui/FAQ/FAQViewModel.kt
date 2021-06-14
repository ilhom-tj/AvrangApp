package tj.colibri.avrang.ui.FAQ

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.network.repositories.faqRepo.FAQRepo

class FAQViewModel(application: Application) : AndroidViewModel(application) {
    private val faqRepo = FAQRepo(application)
    var FAQList = faqRepo.faqs

    fun getAllFAQs(){
        faqRepo.getAllFAQs()
    }
}