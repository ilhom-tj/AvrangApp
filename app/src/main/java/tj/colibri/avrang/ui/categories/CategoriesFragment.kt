package tj.colibri.avrang.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
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
import tj.colibri.avrang.data.categories.SubCategory
import tj.colibri.avrang.data.mock.MockData


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubcategoriesViewModel::class.java)

        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarView = actionBar?.customView
        val title : TextView = actionBarView!!.findViewById(R.id.action_bar_title)
        val icon : ImageView = actionBarView.findViewById(R.id.category_icon)

        title.text = args.category.name
        val imageId = context?.resources?.getIdentifier(args.category.icon, "drawable", activity?.packageName)
        if (imageId != null) {
            icon.setImageResource(imageId)
        }

        categories_recycler_view.adapter = categoriesAdapter
        categories_recycler_view.layoutManager = GridLayoutManager(
            context,
            1,
            LinearLayoutManager.VERTICAL,
            false
        )
        categoriesAdapter.setData(MockData.listOfCategories)
    }

    override fun onSubCategoryClicked(item: SubCategory) {
        val directions = CategoriesFragmentDirections.actionCategoriesFragmentToProductsInCategoriesFragment(item)
        findNavController().navigate(directions)
    }

}