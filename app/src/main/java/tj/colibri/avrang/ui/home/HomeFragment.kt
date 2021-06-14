package tj.colibri.avrang.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_home.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.home.News
import tj.colibri.avrang.data.ApiData.home.Partners
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.ApiData.product.ProductCard2
import tj.colibri.avrang.databinding.FragmentHomeBinding
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features
import tj.colibri.avrang.utils.Search
import java.util.*


@Suppress("DEPRECATION")
class HomeFragment : Fragment(), SliderAdapter.ItemClicked, ProductCardAdapter.ItemClicked,
    SwipeRefreshLayout.OnRefreshListener, PartnersAdapter.ItemClick {

    private lateinit var homeViewModel: HomeViewModel


    private var productCardAdapter = ProductCardAdapter(this, this)
    private var bestProductCardAdapter = ProductCardAdapter(this, this)

    private var promoProductCardAdapter = ProductCardAdapter(this, this)
    private var recomenProductCardAdapter = ProductCardAdapter(this, this)


    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var binding: FragmentHomeBinding

    private var promoSliderAdapter = BannerSliderAdapter(this)
    private var partnersAdapter = PartnersAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliderAdapter = SliderAdapter(this, this)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        search_home_layout.setOnClickListener {
            search_home.isIconified = false
        }
        //  sliderAdapter.setData(MockData.listOfSliderImages)


        imageSlider.setSliderAdapter(sliderAdapter)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.indicatorSelectedColor = Color.WHITE
        imageSlider.indicatorUnselectedColor = Color.GRAY
        imageSlider.scrollTimeInSec = 4
        imageSlider.startAutoCycle()
        home_new_products_recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.homeNewProductsRecyclerView.showShimmer()

        binding.homeBestsellersRecyclerView.showShimmer()
        binding.homeRecommendedRecyclerView.showShimmer()
        binding.homeSaleRecyclerView.showShimmer()
        binding.searchHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    Search(this@HomeFragment).search(query)
                    Features().hideKeyboard(requireActivity())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        binding.refreshLayout.setOnRefreshListener(this)
        observeLayout()
    }


    override fun onSliderItemClicked(item: Sliders) {

    }

    override fun onProductItemClicked(product: ProductCard2) {
        val arg = HomeFragmentDirections.actionNavigationHomeToProductInfoFragment(product.slug)
        findNavController().navigate(arg)
    }

    override fun onAddProductToFavorite(favorite: ProductCard2) {
        homeViewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: ProductCard2) {
        homeViewModel.deleteFavorite(favorite)
    }

    private fun openNews(news: News) {
        val action = HomeFragmentDirections.actionNavigationHomeToInfoContainerFragment(
            news.image,
            news.description,
            news.created_at
        )
        findNavController().navigate(action)
    }

    private fun observeLayout() {
        homeViewModel.getHome().observe(viewLifecycleOwner,  {
            it.let {
                val productInformation = it
                binding.refreshLayout.isRefreshing = false

                productCardAdapter.setData(it.newProducts)
                bestProductCardAdapter.setData(it.popular)
                promoProductCardAdapter.setData(it.promotions)
                recomenProductCardAdapter.setData(it.recomended)

                //НУЖНО БУДЕТ РАЗДЕЛИТЬ ИХ !!!!!

                Log.e("BANNERS", it.banners.toTypedArray().contentToString())

                promoSliderAdapter.setData(it.banners)

                Log.e("dsa",promoSliderAdapter.itemCount.toString())

                sliderAdapter.setData(it.sliders)

                partnersAdapter.setData(it.partners)



                Glide.with(this).load(Const.image_url + it.news[0].image)
                    .into(home_news_1)
                Glide.with(this).load(Const.image_url + it.news[1].image)
                    .into(home_news_2)
                Glide.with(this).load(Const.image_url + it.news[2].image)
                    .into(home_news_3)

                home_news_1.setOnClickListener { openNews(productInformation.news[0]) }
                home_news_2.setOnClickListener { openNews(productInformation.news[1]) }
                home_news_3.setOnClickListener { openNews(productInformation.news[2]) }

                binding.homeNewProductsRecyclerView.hideShimmer()
                binding.homeNewProductsRecyclerView.adapter = productCardAdapter


                binding.homePromoRecyclerView.adapter = promoSliderAdapter


                binding.homeBestsellersRecyclerView.adapter = bestProductCardAdapter
                binding.homeBestsellersRecyclerView.hideShimmer()

                binding.homeRecommendedRecyclerView.adapter = recomenProductCardAdapter
                binding.homeRecommendedRecyclerView.hideShimmer()

                binding.homeSaleRecyclerView.adapter = promoProductCardAdapter
                binding.homeSaleRecyclerView.hideShimmer()

                binding.homePromoSecondRecyclerView.adapter = promoSliderAdapter

                home_partners_recycler_view.adapter = partnersAdapter
                home_partners_recycler_view.layoutManager =
                    GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
            }
        })


    }

    override fun onRefresh() {
        binding.homeNewProductsRecyclerView.showShimmer()
        binding.homeBestsellersRecyclerView.showShimmer()
        binding.homeRecommendedRecyclerView.showShimmer()
        binding.homeSaleRecyclerView.showShimmer()
        observeLayout()
    }

    override fun PartnersClick(partners: Partners) {
        Search(this@HomeFragment).searchBrand(partners)
        Features().hideKeyboard(requireActivity())
    }

}


