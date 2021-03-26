package tj.colibri.avrang.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_home.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.home.News
import tj.colibri.avrang.data.ApiData.product.Rating
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.databinding.FragmentHomeBinding
import tj.colibri.avrang.databinding.ProfileFragmentBinding
import tj.colibri.avrang.utils.ConnectionLive
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features


class HomeFragment : Fragment(), SliderAdapter.ItemClicked, ProductCardAdapter.ItemClicked {

    private lateinit var homeViewModel: HomeViewModel

    private val shimerAdapter = ShimmerProduct(this)

    private var productCardAdapter = ProductCardAdapter(this, this)
    private var bestProductCardAdapter = ProductCardAdapter(this,this)

    private var promoProductCardAdapter = ProductCardAdapter(this,this)
    private var recomenProductCardAdapter = ProductCardAdapter(this,this)


    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var binding : FragmentHomeBinding

    private var promoSliderAdapter = BannerSliderAdapter(this)
    private var partnersAdapter = PartnersAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sliderAdapter = SliderAdapter(this, this)

        homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        search_home_layout.setOnClickListener {
            search_home.isIconified = false
        }
      //  sliderAdapter.setData(MockData.listOfSliderImages)


        imageSlider.setSliderAdapter(sliderAdapter)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.indicatorSelectedColor = Color.WHITE
        imageSlider.indicatorUnselectedColor = Color.GRAY
        imageSlider.scrollTimeInSec = 4
        imageSlider.startAutoCycle()

        home_new_products_recycler_view.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
        binding.homeNewProductsRecyclerView.showShimmer()
        binding.homePromoRecyclerView.showShimmer()
        binding.homeBestsellersRecyclerView.showShimmer()
        binding.homeRecommendedRecyclerView.showShimmer()
        binding.homeSaleRecyclerView.showShimmer()
        binding.homePromoSecondRecyclerView.showShimmer()


        ConnectionLive.init(requireActivity().application)
        ConnectionLive.observe(viewLifecycleOwner, Observer {
            if (it){
                homeViewModel.getHome().observe(viewLifecycleOwner, Observer {
                    it.let {
                        var productInformation = it

                        productCardAdapter.setData(it.newProducts)
                        bestProductCardAdapter.setData(it.popular)
                        promoProductCardAdapter.setData(it.promotions)
                        recomenProductCardAdapter.setData(it.recomended)

                        //НУЖНО БУДЕТ РАЗДЕЛИТЬ ИХ !!!!!
                        promoSliderAdapter.setData(it.banners)

                        sliderAdapter.setData(it.sliders)

                        partnersAdapter.setData(it.partners)

                        Glide.with(this).load(Const.image_url + it.news.get(0).image).into(home_news_1)
                        Glide.with(this).load(Const.image_url + it.news.get(1).image).into(home_news_2)
                        Glide.with(this).load(Const.image_url + it.news.get(2).image).into(home_news_3)

                        home_news_1.setOnClickListener{openNews(productInformation.news.get(0))}
                        home_news_2.setOnClickListener{openNews(productInformation.news.get(1))}
                        home_news_1.setOnClickListener{openNews(productInformation.news.get(2))}

                        binding.homeNewProductsRecyclerView.hideShimmer()
                        binding.homeNewProductsRecyclerView.adapter = productCardAdapter


                        binding.homePromoRecyclerView.adapter = promoSliderAdapter
                        binding.homePromoRecyclerView.hideShimmer()

                        binding.homeBestsellersRecyclerView.adapter = bestProductCardAdapter
                        binding.homeBestsellersRecyclerView.hideShimmer()

                        binding.homeRecommendedRecyclerView.adapter = recomenProductCardAdapter
                        binding.homeRecommendedRecyclerView.hideShimmer()

                        binding.homeSaleRecyclerView.adapter = productCardAdapter
                        binding.homeSaleRecyclerView.hideShimmer()

                        binding.homePromoSecondRecyclerView.adapter = promoSliderAdapter
                        binding.homePromoSecondRecyclerView.hideShimmer()

                        home_partners_recycler_view.adapter = partnersAdapter
                        home_partners_recycler_view.layoutManager = GridLayoutManager(context, 3,  LinearLayoutManager.VERTICAL, false)
                    }
                })
            }
        })

    }



    override fun onSliderItemClicked(item: Sliders) {

    }

    override fun onProductItemClicked(product: ProductCard2) {
        val arg = HomeFragmentDirections.actionNavigationHomeToProductInfoFragment(product)
        findNavController().navigate(arg)
    }

    override fun onAddProductToFavorite(favorite: ProductCard2) {
        homeViewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: ProductCard2) {
        homeViewModel.deleteFavorite(favorite)
    }

    fun openNews(news : News){
        var action = HomeFragmentDirections.actionNavigationHomeToInfoContainerFragment(news.image,news.description,news.created_at)
        findNavController().navigate(action)
    }

}


