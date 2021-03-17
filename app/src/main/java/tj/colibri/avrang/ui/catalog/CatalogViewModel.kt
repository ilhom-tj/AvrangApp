package tj.colibri.avrang.ui.catalog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.colibri.avrang.data.ApiData.Category.CategoryResponse
import tj.colibri.avrang.network.repositories.catalogRepo.CatalogRepo

class CatalogViewModel(application: Application) : AndroidViewModel(application) {

    val catalogRepo = CatalogRepo(application.applicationContext)

    fun getCatalog() : LiveData<CategoryResponse>{
        return catalogRepo.getCatalogs()
    }

}