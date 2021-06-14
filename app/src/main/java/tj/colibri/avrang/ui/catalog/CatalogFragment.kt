package tj.colibri.avrang.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.android.synthetic.main.fragment_home.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CatalogAdapter
import tj.colibri.avrang.data.ApiData.Category.Children
import tj.colibri.avrang.utils.Features
import tj.colibri.avrang.utils.Search

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

        dashboardViewModel.getCatalog().observe(viewLifecycleOwner,  {
            it.let {
                catalogAdapter.setData(it.categories)
            }
        })
        search_catalog.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Search(this@CatalogFragment).search(query.toString())
                Features().hideKeyboard(requireActivity())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onItemClicked(product: Children) {
        val direction = CatalogFragmentDirections.actionNavigationCatalogToCategoriesFragment(product.children.toTypedArray(),product.name)
        findNavController().navigate(direction)
    }
}