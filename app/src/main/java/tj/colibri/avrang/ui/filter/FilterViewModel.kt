package tj.colibri.avrang.ui.filter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tj.colibri.avrang.data.ApiData.Category.CategoryProductRequest
import tj.colibri.avrang.data.ApiData.filter.FilterData
import tj.colibri.avrang.network.repositories.catalogRepo.CatalogRepo

class FilterViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryRepo = CatalogRepo(application.applicationContext)

    fun searchFilter(filterData: FilterData)
    : LiveData<CategoryProductRequest>{
        return categoryRepo.searchProduct(filterData)
    }
//    fun categoryFilter(value : String,priceFro : Float,priceTo: Float,category : String?)
//            : LiveData<CategoryProductRequest>{
//        return categoryRepo.filterForCategory(value,priceFro,priceTo,category)
//    }

}