package tj.colibri.avrang.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tj.colibri.avrang.R
import tj.colibri.avrang.models.Filter.FilterData
import tj.colibri.avrang.models.Home.Partners

class Search(val context: Fragment) {
    fun search(string: String){
        val bundle = Bundle()
        bundle.putParcelable("filterData",FilterData(string,null,null,null,1))
        context.findNavController().navigate(R.id.productsInCategoriesFragment,bundle)
    }

    fun searchBrand(partners: Partners){
        val bundle = Bundle()
        bundle.putParcelable("filterData",FilterData(null,null,null,null,1,brand = partners.id.toString()))
        context.findNavController().navigate(R.id.productsInCategoriesFragment,bundle)
    }
}