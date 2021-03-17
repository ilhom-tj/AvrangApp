package tj.colibri.avrang.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.home_new_products_recycler_view
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CatalogAdapter
import tj.colibri.avrang.data.ApiData.Category.Categories
import tj.colibri.avrang.data.catalog.CatalogItem
import tj.colibri.avrang.data.mock.MockData

class CatalogFragment : Fragment(), CatalogAdapter.ItemClicked {

    private lateinit var dashboardViewModel: CatalogViewModel
    private var catalogAdapter = CatalogAdapter(this, this)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(CatalogViewModel::class.java)
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalog_recycler_view.adapter = catalogAdapter
        catalog_recycler_view.layoutManager = GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)

        dashboardViewModel.getCatalog().observe(viewLifecycleOwner, Observer {
            it.let {
                catalogAdapter.setData(it.categories)
            }
        })
    }

    override fun onItemClicked(catalog: Categories) {
        val direction = CatalogFragmentDirections.actionNavigationCatalogToCategoriesFragment()
        findNavController().navigate(direction)
    }
}