package tj.colibri.avrang.ui.product

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.custom_spinner.view.*
import kotlinx.android.synthetic.main.product_info_fragment.*
import kotlinx.android.synthetic.main.product_info_fragment.product_slider
import kotlinx.android.synthetic.main.product_info_fragment.rv_parent
import kotlinx.android.synthetic.main.product_info_fragment.specifications_recycler_view
import kotlinx.android.synthetic.main.product_info_fragment.users_also_buy_recycler_view
import kotlinx.android.synthetic.main.product_info_fragment_v2.*
import tj.colibri.avrang.MainActivity
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.product.ProductInformation
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.mock.MockData
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.data.product.options.info.ProductInfo
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.databinding.ProductInfoFragmentV2Binding
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features
import java.util.regex.Pattern
import kotlinx.android.synthetic.main.product_info_fragment.installment_recycler_view as installment_recycler_view1
import kotlinx.android.synthetic.main.product_info_fragment.product_old_price as product_old_price1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating as product_rating1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_2nd as product_rating_2nd1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_five_progress as product_rating_five_progress1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_four_count as product_rating_four_count1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_four_progress as product_rating_four_progress1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_one_count as product_rating_one_count1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_qty as product_rating_qty1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_three_count as product_rating_three_count1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_two_count as product_rating_two_count1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_two_progress as product_rating_two_progress1
import kotlinx.android.synthetic.main.product_info_fragment.rating_bar as rating_bar1
import kotlinx.android.synthetic.main.product_info_fragment.show_all_ratings as show_all_ratings1
import kotlinx.android.synthetic.main.product_info_fragment_v2.product_about as product_about1
import kotlinx.android.synthetic.main.product_info_fragment_v2.product_price as product_price1
import kotlinx.android.synthetic.main.product_info_fragment_v2.product_rating_five_count as product_rating_five_count1
import kotlinx.android.synthetic.main.product_info_fragment_v2.product_rating_one_progress as product_rating_one_progress1
import kotlinx.android.synthetic.main.product_info_fragment_v2.product_rating_three_progress as product_rating_three_progress1


class ProductInfoFragment : Fragment(), SliderAdapter.ItemClicked, ProductAlsoBoughtAdapter.ItemClicked {

    private lateinit var viewModel: ProductInfoViewModel
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var optionsAdapter: OptionsAdapter
    private lateinit var specificationsAdapter: SpecificationsAdapter

    private lateinit var favorite: Favorite

    private var productCardAdapter = ProductAlsoBoughtAdapter(this, this)
    private lateinit var productInfo: ProductInformation
    private var banksAdapter = BanksAdapter(this)

    private lateinit var binding: ProductInfoFragmentV2Binding
    private val args: ProductInfoFragmentArgs by navArgs()


    //Rating quantity
    private var rFiveQ = 0;
    private var rFourQ = 0;
    private var rThreeQ = 0;
    private var rTwoQ = 0;
    private var rOneQ = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = ""
        binding =
            DataBindingUtil.inflate(inflater, R.layout.product_info_fragment_v2, container, false)
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProductInfoViewModel::class.java)


        product_name.text = args.name


        //product_price = productInfo.productPrice.toString()

        if (args.discountedPrice != null) {
            product_price.text = args.discountedPrice.toString() + " TJS"
            product_old_price.text = args.price.toString() + " TJS"
        } else {
            product_price.text = args.price.toString() + " TJS"
            product_old_price.visibility = View.GONE
        }

        middle_rating.text = args.rating.rating.toString()
        rating_quantity.text = args.rating.quantity.toString() + " Отзывов"

        product_rating_2nd.text = args.rating.rating.toString()

        //ratings_qty = ""
        product_rating_qty.text = args.rating.quantity.toString()

        viewModel.getProductBySlug(args.slug).observe(viewLifecycleOwner, Observer {
            it.let {
                productInfo = it
                setSliderAdapter(it.product.images.split(","))
                banksAdapter.setData(it.banks)
                productCardAdapter.setData(it.alsoBought)
                SetUpProduct()
            }
        })
        sliderAdapter = SliderAdapter(this, this)
        optionsAdapter = OptionsAdapter(this)
        specificationsAdapter = SpecificationsAdapter(this)

        //  sliderAdapter.setData(productInfo.sliderImages)
        product_slider.setSliderAdapter(sliderAdapter)
        product_slider.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        product_slider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        product_slider.indicatorSelectedColor = Color.WHITE
        product_slider.indicatorUnselectedColor = Color.GRAY
        product_slider.scrollTimeInSec = 4
        product_slider.startAutoCycle()

        rv_parent.adapter = optionsAdapter
        rv_parent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //    optionsAdapter.setData(productInfo.optionsList)

        specifications_recycler_view.adapter = specificationsAdapter
        specifications_recycler_view.layoutManager = GridLayoutManager(
            context,
            1,
            LinearLayoutManager.VERTICAL,
            false
        )
        //   specificationsAdapter.setData(productInfo.specsList)

        users_also_buy_recycler_view.adapter = productCardAdapter
        users_also_buy_recycler_view.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        //   productCardAdapter.setData(productInfo.userAlsoBuyList)

        product_old_price.setPaintFlags(product_old_price.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        show_all_ratings.setOnClickListener {
            val action = ProductInfoFragmentDirections.actionProductInfoFragmentToRatingWithCommentFragment(productInfo.product.rating,productInfo.reviews.toTypedArray())
            findNavController().navigate(action)
        }

        installment_recycler_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        installment_recycler_view.adapter = banksAdapter

        add_cart.setOnClickListener {
            viewModel.addToCart(productInfo.product.id,1).observe(viewLifecycleOwner, Observer {
                it.let {
                    Toast.makeText(requireContext(),"Товар добавлен в корзину",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    @SuppressLint("SetTextI18n")
    fun SetUpProduct() {

        product_code.text = "Код товара: ${productInfo.product.sKU}"

        //product_rating = productInfo.productRating





        //product_about = ""
        product_about.text = productInfo.product.excerpt


        productInfo.reviews.forEach { reviews ->
            if (reviews.rating == 1) rOneQ++;
            if (reviews.rating == 2) rTwoQ++;
            if (reviews.rating == 3) rThreeQ++;
            if (reviews.rating == 4) rFourQ++;
            if (reviews.rating == 5) rFiveQ++;
        }
        //product_rating_five_progress =
        product_rating_five_count.text = rFiveQ.toString()

        product_rating_four_count.text = rFourQ.toString()

        product_rating_three_count.text = rThreeQ.toString()

        product_rating_two_count.text = rTwoQ.toString()

        product_rating_one_count.text = rOneQ.toString()

        SetUpProgress()
        //  SetUpRating()


    }

    fun setSliderAdapter(imglist : List<String>){
        var index = 0;
        var sliderList = ArrayList<Sliders>()
        imglist.forEach { img ->
            index++
            val spl = 34.toChar();
            val readyimg = Const.image_url + img.replace("\\","")
                .replace("[","")
                .replace("]","")
                .replace(spl.toString(),"")
            val slider = Sliders(index,readyimg,readyimg,readyimg)
            sliderList.add(slider)
        }
        sliderAdapter.setData(sliderList)
    }


    fun SetUpProgress(){

        rating_bar.rating = productInfo.product.rating.rating.toFloat()
        product_rating_2nd.setText(productInfo.product.rating.rating.toString())

        product_rating_qty.setText(productInfo.product.rating.quantity.toString() + "Отзывов" )
        //Set up value and max value
        val max = productInfo.product.rating.rating
        product_rating_five_progress.max = max
        product_rating_five_progress.setProgress(rFiveQ)

        product_rating_four_progress.max = max
        product_rating_four_progress.setProgress(rFourQ)

        product_rating_three_progress.max = max
        product_rating_three_progress.setProgress(rThreeQ)

        product_rating_two_progress.max = max
        product_rating_two_progress.setProgress(rTwoQ)

        product_rating_one_progress.max = max
        product_rating_one_progress.setProgress(rOneQ)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.product_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_to_favorite) {

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSliderItemClicked(item: Sliders) {

    }

    override fun onProductItemClicked(product: ProductCard2) {
        val action = ProductInfoFragmentDirections.actionProductInfoFragmentSelf(product.slug,product.name,
            product.newPrice.toFloat(),product.productPrice.toFloat(),product.rating,product.isFavorite,product.images
        )
        findNavController().navigate(action)
    }


    override fun onAddProductToFavorite(favorite: Favorite) {
        viewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: Favorite) {
        viewModel.deleteFavorite(favorite)
    }

}