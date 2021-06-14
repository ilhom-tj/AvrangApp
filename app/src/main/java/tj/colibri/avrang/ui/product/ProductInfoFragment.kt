package tj.colibri.avrang.ui.product

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.product_info_fragment_v2.*
import tj.colibri.avrang.MainActivity
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.product.Banks
import tj.colibri.avrang.data.ApiData.product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.data.ApiData.product.Reviews
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.ApiData.product.ProductCard2
import tj.colibri.avrang.data.mock.MockData
import tj.colibri.avrang.databinding.ProductInfoFragmentV2Binding
import tj.colibri.avrang.utils.Animator
import tj.colibri.avrang.utils.Features

@Suppress("SENSELESS_COMPARISON")
class ProductInfoFragment : Fragment(), SliderAdapter.ItemClicked,
    ProductCardAdapter.ItemClicked, BanksAdapter.ItemClicked {

    private lateinit var viewModel: ProductInfoViewModel
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var optionsAdapter: OptionsAdapter
    private lateinit var specificationsAdapter: SpecificationsAdapter


    private var productCardAdapter = ProductCardAdapter(this, this)
    private lateinit var productInfo: ProductInfortmation2
    private var banksAdapter = BanksAdapter(this,this)

    private lateinit var binding: ProductInfoFragmentV2Binding
    private val args: ProductInfoFragmentArgs by navArgs()
    private var isFavorite: Boolean = false

    //tj.colibri.avrang.data.ApiData.product.ProductInfo.Rating quantity
    private var rFiveQ = 0
    private var rFourQ = 0
    private var rThreeQ = 0
    private var rTwoQ = 0
    private var rOneQ = 0
    private var currentPrice: Double = 0.0

    private lateinit var favoriteButton: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).supportActionBar?.title = ""
        binding =
            DataBindingUtil.inflate(inflater, R.layout.product_info_fragment_v2, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductInfoViewModel::class.java)

        viewModel.getProductBySlug(args.slug).observe(viewLifecycleOwner,  {
            SetUpProduct(it)
        })

        sliderAdapter = SliderAdapter(this, this)
        optionsAdapter = OptionsAdapter(this)
        specificationsAdapter = SpecificationsAdapter(this)

        //  sliderAdapter.setData(productInfo.sliderImages)
        product_slider.setSliderAdapter(sliderAdapter)
        product_slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
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

        binding.productOldPrice.paintFlags =
            binding.productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.showAllRatings.setOnClickListener {
            var rev = emptyList<Reviews>()
            if (productInfo.reviews != null) {
                rev = productInfo.reviews!!
            }
            val action =
                ProductInfoFragmentDirections.actionProductInfoFragmentToRatingWithCommentFragment(
                    productInfo.product.rating,
                    rev.toTypedArray()
                )
            findNavController().navigate(action)
        }

        binding.installmentRecyclerView.layoutManager =
            GridLayoutManager(context, 1)
        binding.installmentRecyclerView.adapter = banksAdapter

        binding.addCart.setOnClickListener {
            addToCart(0,null)
        }

    }

    private fun addToCart(state: Int, bank : Banks?) {
        when (state) {
            1 ->{
                if (bank != null){
                    val bundle = Bundle()
                    bundle.putInt("bankID",bank.id)
                    bundle.putInt("quickProduct",productInfo.product.id)
                    bundle.putDouble("price",product_price.text.toString().trim().replace("TJS","").toDouble())

                    findNavController().navigate(R.id.instalationFragment,bundle)
                }
            }
            0->{
                val cartItem = CartItem(
                    productInfo.product.id,
                    productInfo.product.slug,
                    productInfo.product.sKU.toString(),
                    productInfo.product.name,
                    1,
                    productInfo.product.images[0],
                    currentPrice,
                    0, 50
                )

                viewModel.addToLocalCart(cartItem)
                viewModel.addToCart(productInfo.product.id)
                viewModel.addCheckOut(productInfo.product.id, 1)
                viewModel.cartRepository.checkOutList.observe(viewLifecycleOwner, { items ->
                    viewModel.cartRepository.cartIndexes.observe(viewLifecycleOwner, {
                        it.let {
                            Log.e("mass", it.toIntArray().contentToString())
                            viewModel.cartRepository.updateCart(it, items)
                            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
                                .getOrCreateBadge(R.id.navigation_cart).number = it.size
                            //          BottomMenuHelper.showBadge(requireContext(),requireActivity().findViewById(R.id.nav_view),R.id.navigation_cart,it.size)
                        }
                    })
                })
                Toast.makeText(requireContext(), "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }





    }

    @SuppressLint("SetTextI18n")
    private fun SetUpProduct(info: ProductInfortmation2) {
        product_info_layout.visibility = View.VISIBLE
        product_loading.visibility = View.GONE
        productInfo = info
        setSliderAdapter(info.product.images)
        banksAdapter.setData(info.banks)
        productCardAdapter.setData(info.alsoBought)
        if (info.attributes != null) {
            specificationsAdapter.setData(info.attributes)
        }
        binding.productName.text = info.product.name
        isFavorite = info.product.isFavorite

        viewModel.products.observe(viewLifecycleOwner,  {
            if (it.isNotEmpty()) {
                it.forEach { item ->
                    if (item.id == info.product.id) {
                        add_cart.isClickable = false
                        add_cart_label.text = "Товар уже в корзине"
                    }
                }
            }
        })
        if (info.product.newPrice != 0.0) {
            binding.productPrice.text = info.product.newPrice.toString() + " TJS"
            currentPrice = info.product.newPrice
            binding.productOldPrice.text = info.product.productPrice.toString() + " TJS"
        } else {
            binding.productPrice.text = info.product.productPrice.toString() + " TJS"
            currentPrice = info.product.productPrice
            binding.productOldPrice.visibility = View.GONE
        }

        middle_rating.text = info.product.rating.rating.toString()
        rating_quantity.text = info.product.rating.quantity.toString() + " Отзывов"

        binding.productRating2nd.text = info.product.rating.rating.toString()

        binding.productRatingQty.text = info.product.rating.quantity.toString()

        product_code.text = "Код товара: ${productInfo.product.sKU}"

        binding.productAbout.text = productInfo.product.excerpt

        if (productInfo.reviews != null) {
            productInfo.reviews!!.forEach { reviews ->
                if (reviews.rating == 1) rOneQ++
                if (reviews.rating == 2) rTwoQ++
                if (reviews.rating == 3) rThreeQ++
                if (reviews.rating == 4) rFourQ++
                if (reviews.rating == 5) rFiveQ++
            }
        }

        binding.productRatingFiveCount.text = rFiveQ.toString()

        binding.productRatingFourCount.text = rFourQ.toString()

        binding.productRatingThreeCount.text = rThreeQ.toString()

        binding.productRatingTwoCount.text = rTwoQ.toString()

        binding.productRatingOneCount.text = rOneQ.toString()


        if (info.product.isFavorite) {
            favoriteButton.setIcon(R.drawable.ic_baseline_favorite_24)
        } else {
            favoriteButton.setIcon(R.drawable.ic_baseline_favorite_border_24)
        }

        favoriteButton.setOnMenuItemClickListener {
            if (isFavorite) {
                Log.e("IsFav", isFavorite.toString())
                viewModel.deleteFavorite(info.product)
                favoriteButton.setIcon(R.drawable.ic_baseline_favorite_border_24)
                isFavorite = false
            } else {
                viewModel.addFavorite(info.product)
                favoriteButton.setIcon(R.drawable.ic_baseline_favorite_24)
                isFavorite = true
                Log.e("IsFav", isFavorite.toString())
            }
            true
        }
      //  Log.e("SIZE",specFullSize.toString())
        binding.specificationsRecyclerView.isVerticalScrollBarEnabled = false

        if (info.attributes != null){
            val specFullSize = (info.attributes.size * 30).dp
            if (info.attributes.size > 5){
                binding.specificationsRecyclerView.layoutParams.height = 150.dp
                binding.showAllSpecs.visibility = View.VISIBLE
                binding.showAllSpecs.setOnClickListener {
                    Animator().slideView(binding.specificationsRecyclerView,150.dp,specFullSize)
                }
            }else{
                binding.showAllSpecs.visibility = View.GONE
            }
        }else{
            binding.showAllSpecs.visibility = View.GONE
        }



        SetUpProgress()


    }


    private fun setSliderAdapter(imglist: List<String>) {
        //   Log.e("imgs",imglist.get(0))
        sliderAdapter.setData(Features().toSliderItemFromString(imglist))
    }


    @SuppressLint("SetTextI18n")
    fun SetUpProgress() {

        binding.ratingBar.rating = productInfo.product.rating.rating.toFloat()
        binding.productRating2nd.text = productInfo.product.rating.rating.toString()

        binding.productRatingQty.text = productInfo.product.rating.quantity.toString() + " Отзывов"
        //Set up value and max value
        val max = productInfo.product.rating.rating
        Log.e("COUNT", max.toString())
        binding.productRatingFiveProgress.max = max
        binding.productRatingFiveProgress.progress = rFiveQ

        binding.productRatingFourProgress.max = max
        binding.productRatingFourProgress.progress = rFourQ

        binding.productRatingThreeProgress.max = max
        binding.productRatingThreeProgress.progress = rThreeQ

        binding.productRatingTwoProgress.max = max
        binding.productRatingTwoProgress.progress = rTwoQ

        binding.productRatingOneProgress.max = max
        binding.productRatingOneProgress.progress = rOneQ

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.product_menu, menu)

        favoriteButton = menu.findItem(R.id.add_to_favorite)

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSliderItemClicked(item: Sliders) {

    }

    override fun onProductItemClicked(product: ProductCard2) {
        val action = ProductInfoFragmentDirections.actionProductInfoFragmentSelf(product.slug)
        findNavController().navigate(action)
    }


    override fun onAddProductToFavorite(favorite: ProductCard2) {
        viewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: ProductCard2) {
        viewModel.deleteFavorite(favorite)
    }

    override fun bankClick(bank: Banks) {
        addToCart(1,bank)
    }

    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

}