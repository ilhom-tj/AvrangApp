package tj.colibri.avrang.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.categories_fragment.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import tj.colibri.avrang.MainActivity
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CategoriesAdapter
import tj.colibri.avrang.adapters.SubCategoriesAdapter
import tj.colibri.avrang.data.ApiData.Category.Children
import tj.colibri.avrang.data.ApiData.filter.FilterData
import tj.colibri.avrang.utils.Features
import tj.colibri.avrang.utils.Search


class CategoriesFragment : Fragment(), SubCategoriesAdapter.ItemClicked, CategoriesAdapter.ItemClicked {

    private lateinit var viewModel: SubcategoriesViewModel
    private val args: CategoriesFragmentArgs by navArgs()
    private var categoriesAdapter = CategoriesAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categories_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubcategoriesViewModel::class.java)

        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarView = actionBar?.customView

        val title : TextView = actionBarView!!.findViewById(R.id.action_bar_title)

        title.text = args.parentTitle


        categoriesAdapter.setData(args.children.toList())
        categories_recycler_view.adapter = categoriesAdapter
        categories_recycler_view.layoutManager = GridLayoutManager(
            context,
            1,
            LinearLayoutManager.VERTICAL,
            false
        )

        search_subcategories.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Search(this@CategoriesFragment).search(query.toString())
                Features().hideKeyboard(requireActivity())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun onParentClicked(item: Children) {
        val bundle  = Bundle()
        val filterData = FilterData(null,null,null,item.id.toString(),null)
        bundle.putParcelable("filterData",filterData)
        findNavController().navigate(R.id.productsInCategoriesFragment,bundle)
    }

    override fun onSubCategoryClicked(item: Children) {
        val bundle  = Bundle()
        val filterData = FilterData(null,null,null,item.id.toString(),null)
        bundle.putParcelable("filterData",filterData)
        findNavController().navigate(R.id.productsInCategoriesFragment,bundle)
    }

}