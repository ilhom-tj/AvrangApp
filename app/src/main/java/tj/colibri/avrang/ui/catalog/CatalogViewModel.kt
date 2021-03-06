package tj.colibri.avrang.ui.catalog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tj.colibri.avrang.models.Category.CategoryResponse
import tj.colibri.avrang.network.repositories.catalogRepo.CatalogRepo

class CatalogViewModel(application: Application) : AndroidViewModel(application) {

    private val catalogRepo = CatalogRepo(application.applicationContext)

    fun getCatalog() : LiveData<CategoryResponse>{
        return catalogRepo.getCatalogs()
    }

}