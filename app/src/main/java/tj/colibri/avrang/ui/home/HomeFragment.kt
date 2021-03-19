package tj.colibri.avrang.ui.home

import android.graphics.Color
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
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_home.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.home.News
import tj.colibri.avrang.data.ApiData.product.Rating
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.utils.ConnectionLive
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features


class HomeFragment : Fragment(), SliderAdapter.ItemClicked, ProductCardAdapter.ItemClicked {

    private lateinit var homeViewModel: HomeViewModel

    private var productCardAdapter = ProductCardAdapter(this, this)
    private var bestProductCardAdapter = ProductCardAdapter(this,this)

    private var promoProductCardAdapter = ProductCardAdapter(this,this)
    private var recomenProductCardAdapter = ProductCardAdapter(this,this)


    private lateinit var sliderAdapter: SliderAdapter
    private var promoSliderAdapter = BannerSliderAdapter(this)
    private var partnersAdapter = PartnersAdapter(this)

    private lateinit var favorite : Favorite

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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

                    }
                })
            }
        })


//        homeViewModel.newProducts.observe(viewLifecycleOwner, Observer {
//            it.let {
//                productCardAdapter.setData(it)
//            }
//        })
//        homeViewModel.popularProducts.observe(viewLifecycleOwner, Observer {
//            it.let {
//                bestProductCardAdapter.setData(it)
//            }
//        })
//        homeViewModel.recommendedProducts.observe(viewLifecycleOwner, Observer {
//            it.let {
//                recomenProductCardAdapter.setData(it)
//            }
//        })
//        homeViewModel.maxDisProducts.observe(viewLifecycleOwner, Observer {
//            it.let {
//                promoProductCardAdapter.setData(it)
//            }
//        })

        home_new_products_recycler_view.adapter = productCardAdapter
        //        productCardAdapter.setData(MockData.listOfProducts)
//

        home_promo_recycler_view.adapter = promoSliderAdapter
        home_promo_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

//
        home_bestsellers_recycler_view.adapter = bestProductCardAdapter
        home_bestsellers_recycler_view.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
//
        home_recommended_recycler_view.adapter = recomenProductCardAdapter
        home_recommended_recycler_view.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)

//
        home_promo_second_recycler_view.adapter = promoSliderAdapter
        home_promo_second_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
 //       promoSliderAdapter.setData(MockData.listOfSliderImages)

        home_sale_recycler_view.adapter = productCardAdapter
        home_sale_recycler_view.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)


        home_partners_recycler_view.adapter = partnersAdapter
        home_partners_recycler_view.layoutManager = GridLayoutManager(context, 3,  LinearLayoutManager.VERTICAL, false)
     //   partnersAdapter.setData(MockData.listOfSliderImages)

    }



    override fun onSliderItemClicked(item: Sliders) {

    }

    override fun onProductItemClicked(product: ProductCard2) {
        val arg = HomeFragmentDirections.actionNavigationHomeToProductInfoFragment(product)
        findNavController().navigate(arg)
    }

    override fun onAddProductToFavorite(favorite: Favorite) {
        homeViewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: Favorite) {
        homeViewModel.deleteFavorite(favorite)
    }

    fun openNews(news : News){
        var action = HomeFragmentDirections.actionNavigationHomeToInfoContainerFragment(news.image,news.description,news.created_at)
        findNavController().navigate(action)
    }

}


