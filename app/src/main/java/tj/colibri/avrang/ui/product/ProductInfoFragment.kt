package tj.colibri.avrang.ui.product

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.product_info_fragment.*
import kotlinx.android.synthetic.main.product_info_fragment.product_slider
import kotlinx.android.synthetic.main.product_info_fragment.rv_parent
import kotlinx.android.synthetic.main.product_info_fragment.specifications_recycler_view
import kotlinx.android.synthetic.main.product_info_fragment.users_also_buy_recycler_view
import kotlinx.android.synthetic.main.product_info_fragment_v2.*
import tj.colibri.avrang.MainActivity
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.databinding.ProductInfoFragmentV2Binding
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.utils.Features
import java.util.*


@Suppress("SENSELESS_COMPARISON")
class ProductInfoFragment : Fragment(), SliderAdapter.ItemClicked,
    ProductCardAdapter.ItemClicked {

    private lateinit var viewModel: ProductInfoViewModel
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var optionsAdapter: OptionsAdapter
    private lateinit var specificationsAdapter: SpecificationsAdapter


    private lateinit var favorite: Favorite

    private var productCardAdapter = ProductCardAdapter(this, this)
    private lateinit var productInfo: ProductInfortmation2
    private var banksAdapter = BanksAdapter(this)

    private lateinit var binding: ProductInfoFragmentV2Binding
    private val args: ProductInfoFragmentArgs by navArgs()


    //tj.colibri.avrang.data.ApiData.product.ProductInfo.Rating quantity
    private var rFiveQ = 0;
    private var rFourQ = 0;
    private var rThreeQ = 0;
    private var rTwoQ = 0;
    private var rOneQ = 0;
    private var currentPrice: Double = 0.0
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProductInfoViewModel::class.java)


        product_name.text = args.product.name


        //product_price = productInfo.productPrice.toString()

        //    Log.e("args_price",args.product.productPrice.toString())
        //     Log.e("args_price",args.product.newPrice.toString())
        //   binding.productPrice.text = "ajja"

        if (args.product.newPrice != 0.0) {
            binding.productPrice.text = args.product.newPrice.toString() + " TJS"
            currentPrice = args.product.newPrice
            binding.productOldPrice.text = args.product.productPrice.toString() + " TJS"
        } else {
            binding.productPrice.text = args.product.productPrice.toString() + " TJS"
            currentPrice = args.product.productPrice
            binding.productOldPrice.visibility = View.GONE
        }

        middle_rating.text = args.product.rating.rating.toString()
        rating_quantity.text = args.product.rating.quantity.toString() + " Отзывов"

        binding.productRating2nd.text = args.product.rating.rating.toString()

        //ratings_qty = ""
        binding.productRatingQty.text = args.product.rating.quantity.toString()

        Log.e("SLUG", args.product.slug)

        viewModel.getProductBySlug(args.product.slug).observe(viewLifecycleOwner, Observer {
            Log.e("dsa", it.toString())
            it.let {
                productInfo = it
                setSliderAdapter(it.product.images)
                banksAdapter.setData(it.banks)
                productCardAdapter.setData(it.alsoBought)
                specificationsAdapter.setData(it.attributes)
                SetUpProduct()
            }
        })
        sliderAdapter = SliderAdapter(this, this)
        optionsAdapter = OptionsAdapter(this)
        specificationsAdapter = SpecificationsAdapter(this)

        //  sliderAdapter.setData(productInfo.sliderImages)
        product_slider.setSliderAdapter(sliderAdapter)
        product_slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        product_slider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        product_slider.indicatorSelectedColor = Color.WHITE
        product_slider.indicatorUnselectedColor = Color.GRAY
        product_slider.scrollTimeInSec = 4
        product_slider.startAutoCycle()


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

        binding.productOldPrice.setPaintFlags(binding.productOldPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        binding.showAllRatings.setOnClickListener {
            val action =
                ProductInfoFragmentDirections.actionProductInfoFragmentToRatingWithCommentFragment(
                    productInfo.product.rating,
                    productInfo.reviews.toTypedArray()
                )
            findNavController().navigate(action)
        }

        binding.installmentRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.installmentRecyclerView.adapter = banksAdapter

        add_cart.setOnClickListener {
            var cartItem = CartItem(
                productInfo.product.id,
                productInfo.product.slug,
                productInfo.product.sKU.toString(),
                productInfo.product.name,
                1,
                productInfo.product.images[0],
                currentPrice,
                0,50
            )
            viewModel.addToLocalCart(cartItem)
            viewModel.addToCart(productInfo.product.id)
            viewModel.addCheckOut(productInfo.product.id, 1)
            viewModel.cartIndexs.observe(viewLifecycleOwner, Observer {
                it.let {
                    viewModel.checkOut.value?.let { it1 ->
                        viewModel.updateCart(
                            it.toMutableList(),
                            it1
                        )
                    }
                }
            })
            Toast.makeText(requireContext(), "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("SetTextI18n")
    fun SetUpProduct() {

        product_code.text = "Код товара: ${productInfo.product.sKU}"

        //product_rating = productInfo.productRating


        //product_about = ""
        binding.productAbout.text = productInfo.product.excerpt


        productInfo.reviews.forEach { reviews ->
            if (reviews.rating == 1) rOneQ++;
            if (reviews.rating == 2) rTwoQ++;
            if (reviews.rating == 3) rThreeQ++;
            if (reviews.rating == 4) rFourQ++;
            if (reviews.rating == 5) rFiveQ++;
        }
        //product_rating_five_progress =
        binding.productRatingFiveCount.text = rFiveQ.toString()

        binding.productRatingFourCount.text = rFourQ.toString()

        binding.productRatingThreeCount.text = rThreeQ.toString()

        binding.productRatingTwoCount.text = rTwoQ.toString()

        binding.productRatingOneCount.text = rOneQ.toString()

        SetUpProgress()
        //  SetUpRating()


    }

    fun setSliderAdapter(imglist: List<String>) {
        //   Log.e("imgs",imglist.get(0))
        sliderAdapter.setData(Features().toSliderItemFromString(imglist))
    }


    fun SetUpProgress() {

        binding.ratingBar.rating = productInfo.product.rating.rating.toFloat()
        binding.productRating2nd.setText(productInfo.product.rating.rating.toString())

        binding.productRatingQty.setText(productInfo.product.rating.quantity.toString() + " Отзывов")
        //Set up value and max value
        val max = productInfo.product.rating.rating
        binding.productRatingFiveProgress.max = max
        binding.productRatingFiveProgress.setProgress(rFiveQ)

        binding.productRatingFourProgress.max = max
        binding.productRatingFourProgress.setProgress(rFourQ)

        binding.productRatingThreeProgress.max = max
        binding.productRatingThreeProgress.setProgress(rThreeQ)

        binding.productRatingTwoProgress.max = max
        binding.productRatingTwoProgress.setProgress(rTwoQ)

        binding.productRatingOneProgress.max = max
        binding.productRatingOneProgress.setProgress(rOneQ)

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
        val action = ProductInfoFragmentDirections.actionProductInfoFragmentSelf(product)
        findNavController().navigate(action)
    }


    override fun onAddProductToFavorite(favorite: Favorite) {
        viewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: Favorite) {
        viewModel.deleteFavorite(favorite)
    }

}