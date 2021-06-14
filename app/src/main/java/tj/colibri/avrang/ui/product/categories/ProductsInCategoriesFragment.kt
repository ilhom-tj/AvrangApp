package tj.colibri.avrang.ui.product.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.products_in_categories_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.ProductCardAdapter
import tj.colibri.avrang.models.Filter.FilterData
import tj.colibri.avrang.data.Mock.MockData
import tj.colibri.avrang.models.Product.ProductCard2
import tj.colibri.avrang.ui.filter.FilterFragment
import tj.colibri.avrang.utils.SubtitleRadioButton


@Suppress("NAME_SHADOWING")
class ProductsInCategoriesFragment : Fragment(), ProductCardAdapter.ItemClicked {

    private lateinit var viewModel: ProductsInCategoriesViewModel
    private lateinit var productCardAdapter: ProductCardAdapter

    private var pageIndex = 1
    private var maxPages = 0
    private var productsQty = 0

    private var ISLastPage: Boolean = false

    private var ISLoading: Boolean = false


    private lateinit var filterData: FilterData
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

       val bundle = this.arguments

        if (bundle?.get("filterData") != null) {
            filterData = bundle.getParcelable("filterData")!!
            if (filterData.q != null) {
                search_products.setQuery(filterData.q, true)
            }
            search()
        }


        products_in_categorires_recycler_view.adapter = productCardAdapter
        val gridLayoutManager = GridLayoutManager(context, 2)
        products_in_categorires_recycler_view.layoutManager = gridLayoutManager


        scrolling.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, _, _, _ ->
            val diff: Int =
                scrolling.getChildAt(scrolling.childCount - 1).bottom - (scrolling.height + scrolling
                    .scrollY)

            if (diff == 0) {
                //                        getPlaylistFromServer("more");
                loadMore()
            }
        }
        )

        productCardAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                gridLayoutManager.scrollToPositionWithOffset(positionStart, 0)
            }
        })

        setFragmentResultListener(FilterFragment.REQUEST_KEY) { _, bundle ->
            val filter = bundle.getParcelable<FilterData>("filterData")
            if (filter != null) {
                productCardAdapter.removeAll()
                filterData = filter
                Log.e("FILTERS",filterData.toString())
                search()
            }

        }




        sorting_label.setOnClickListener {
            ShowFilterSheet()
        }

        search_products.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    @SuppressLint("InflateParams")
    fun ShowFilterSheet() {
        bottomsheet.showWithSheetView(
            LayoutInflater.from(requireContext())
                .inflate(R.layout.products_categories_filters, null)
        )
        val filterGroup = bottomsheet.findViewById<RadioGroup>(R.id.categories_filter)

        val filters = MockData.listOfFilterRadio
        for (filter in filters) {
            val customRadioView = layoutInflater.inflate(R.layout.cst_radiobutton_subtitle, null)
            val radioButton = customRadioView.findViewById<SubtitleRadioButton>(R.id.radio)
            radioButton.id = filter.id
            radioButton.title = filter.title
            radioButton.subtitle = filter.subtitle
            filterGroup.addView(customRadioView)
        }
        filterGroup.setOnCheckedChangeListener { _, checkedId ->
            Log.e("ID", checkedId.toString())
            filterData.pages = null
            when (checkedId) {
                1 ->
                    filterData.filter = "new"
                2 ->
                    filterData.filter = "popular"
                3 ->
                    filterData.filter = "rating"
                4 ->
                    filterData.sort = "cheap"
                5 ->
                    filterData.sort = "expensive"
            }
            val newFilter = FilterData(
                filterData.q,
                null,
                null,
                filterData.currentCategories,
                null,
                null,
                filterData.sort,
                filterData.filter

            )
            filterData = newFilter
            search()
            bottomsheet.dismissSheet()
        }

    }

    @SuppressLint("SetTextI18n")
    fun SetProductQty() {
        products_qty.text = "$productsQty товаров"
    }

    override fun onProductItemClicked(product: ProductCard2) {
        val args =
            ProductsInCategoriesFragmentDirections.actionProductsInCategoriesFragmentToProductInfoFragment(
                product.slug
            )
        findNavController().navigate(args)
    }

    override fun onAddProductToFavorite(favorite: ProductCard2) {
        viewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: ProductCard2) {
        viewModel.deleteFavorite(favorite)
    }

    private fun loadMore() {

        if (pageIndex <= maxPages) {
            pageIndex++
            filterData.pages = pageIndex
            viewModel.search(filterData).observe(viewLifecycleOwner, {
                productCardAdapter.addMoreProducts(it.products.products)
                productsQty += it.products.products.size
                SetProductQty()
                maxPages = it.products.links.total_pages
                if (it.products.products.size < 0) {
                    ISLastPage = true
                }
                ISLoading = false
            })
        } else {
            ISLastPage = true
        }
    }

    fun search() {
        viewModel.search(filterData).observe(viewLifecycleOwner, {
            it.let {
                val cat = it
                filterData.priceFrom = it.priceRangeFrom.toFloat()
                filterData.priceTo = it.priceRangeTo.toFloat()
                maxPages = it.products.links.total_pages
                productCardAdapter.setData(it.products.products)
                productsQty = it.products.products.size
                SetProductQty()
                filters_label.setOnClickListener {
                    val action =
                        ProductsInCategoriesFragmentDirections.actionProductsInCategoriesFragmentToFilterFragment(
                            filterData, cat
                        )
                    findNavController().navigate(action)
                }
            }
        })
    }


}