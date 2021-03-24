package tj.colibri.avrang.ui.product.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.products_in_categories_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.*
import tj.colibri.avrang.MainActivity
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.ProductCardAdapter
import tj.colibri.avrang.data.mock.MockData
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.utils.Features
import tj.colibri.avrang.utils.SubtitleRadioButton
import kotlinx.android.synthetic.main.products_in_categories_fragment.bottomsheet as bottomsheet1

class ProductsInCategoriesFragment : Fragment(), ProductCardAdapter.ItemClicked {

    private lateinit var viewModel: ProductsInCategoriesViewModel
    private lateinit var productCardAdapter: ProductCardAdapter

    private val args: ProductsInCategoriesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.products_in_categories_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsInCategoriesViewModel::class.java)

        productCardAdapter = ProductCardAdapter(this, this)

        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarView = actionBar?.customView
        val title: TextView = actionBarView!!.findViewById(R.id.action_bar_title)
        title.text = args.subCategory.title

        products_in_categorires_recycler_view.adapter = productCardAdapter
        products_in_categorires_recycler_view.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
      //  productCardAdapter.setData(MockData.listOfProducts)

        filters_label.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_productsInCategoriesFragment_to_filterFragment)
        }


        sorting_label.setOnClickListener {
            ShowFilterSheet()
        }


    }

    fun ShowFilterSheet() {
        bottomsheet.showWithSheetView(
            LayoutInflater.from(requireContext())
                .inflate(R.layout.products_categories_filters, null)
        )
        var filterGroup = bottomsheet.findViewById<RadioGroup>(R.id.categories_filter)

        val filters = MockData.listOfFilterRadio
        for (filter in filters) {
            var customRadioView = layoutInflater.inflate(R.layout.cst_radiobutton_subtitle, null)
            var radioButton = customRadioView.findViewById<SubtitleRadioButton>(R.id.radio)
            radioButton.id = filter.id
            radioButton.title = filter.title
            radioButton.subtitle = filter.subtitle
            filterGroup.addView(customRadioView)
        }
        filterGroup.setOnCheckedChangeListener { group, checkedId ->
//            if (checkedId == 1) {
//                productCardAdapter.sortByDate()
//            } else if (checkedId == 2) {
//                productCardAdapter.sortBySaleIndex()
//            } else if (checkedId == 3) {
//                productCardAdapter.sortByRating()
//            } else if (checkedId == 4) {
//                productCardAdapter.sortByLowPrice()
//            } else if (checkedId == 5) {
//                productCardAdapter.sortByHightPrice()
//            } else if (checkedId == 6) {
//                productCardAdapter.sortByDiscount()
//
//            }
            bottomsheet.dismissSheet()
        }

}

override fun onProductItemClicked(product: ProductCard2) {
    findNavController().navigate(R.id.action_productsInCategoriesFragment_to_productInfoFragment)
}

override fun onAddProductToFavorite(favorite: ProductCard2) {
    viewModel.addFavorite(favorite)
}

override fun onRemoveClickListener(favorite: ProductCard2) {
    viewModel.deleteFavorite(favorite)
}

}