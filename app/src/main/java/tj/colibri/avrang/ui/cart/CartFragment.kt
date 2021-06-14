package tj.colibri.avrang.ui.cart

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.cart_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CartItemsAdapter
import tj.colibri.avrang.data.Cart.CartItem
import tj.colibri.avrang.utils.SessionManager
import java.text.DecimalFormat

class CartFragment : Fragment(), CartItemsAdapter.CartItemCliked {

    private lateinit var viewModel: CartViewModel
    private lateinit var cartItemsAdapter: CartItemsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cartItemsAdapter = CartItemsAdapter(this, this)

        cart_recycler_view.adapter = cartItemsAdapter
        cart_recycler_view.layoutManager =
            GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)




       // var checkOutItems = listOf<CheckOutItem>()
        viewModel.indexes.observe(viewLifecycleOwner,  {
            it.let {
       //         checkOutItems = it
                it.forEach { items ->
                    viewModel.updateCartItemQunatity(items)
                }
            }
        })


        val bottomnav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        viewModel.getCartItems.observe(viewLifecycleOwner,  {
            it.let {
                cartItemsAdapter.setData(it)
                if (it.size == 0){
                    bottomnav.removeBadge(R.id.navigation_cart)
                }else{
                    bottomnav.getOrCreateBadge(R.id.navigation_cart).number = it.size
                }
            }
        })


        viewModel.totalPrice.observe(viewLifecycleOwner,  {
            it.let {
                if (it != null) {
                    cart_total.text = DecimalFormat("##.##").format(it) + " TJS"
                 //  totalPrice = it
                } else {
                    cart_total.text = "0 TJS"
                }
            }

        })

        viewModel.totalBonuses.observe(viewLifecycleOwner,  {
            it.let {
                //totalBonuses = it ?: 0
            }
        })

        cart_check_out.setOnClickListener {
            if (SessionManager(requireContext()).getToken() != "error") {
                if (!cart_total.text.equals("0 TJS")) {
                    findNavController().navigate(R.id.action_cartFragment_to_checkOutFragment)
                } else {
                    Toast.makeText(requireContext(), "Нет товаров в корзине", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                findNavController().navigate(R.id.loginFragment)
            }

        }
        cart_check_installation.setOnClickListener {
            if (!cart_total.text.equals("0 TJS")) {
                findNavController().navigate(R.id.instalationFragment)
            } else {
                Toast.makeText(requireContext(), "Нет товаров в корзине", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun itemClickListener(cartItem: CartItem) {
        val action = CartFragmentDirections.actionNavigationCartToProductInfoFragment(cartItem.slug)
        findNavController().navigate(action)
    }

    override fun onPlusQuantityClick(cartItem: CartItem, quantity: Int) {
        cartItem.quantity = quantity
        viewModel.cartRepo.addToCheckOut(cartItem.id, cartItem.quantity)
        viewModel.cartRepo.addToCart(cartItem)
    }

    override fun onMinusQuantityClick(cartItem: CartItem, quantity: Int) {
        cartItem.quantity = quantity
        viewModel.cartRepo.addToCheckOut(cartItem.id, cartItem.quantity)
        viewModel.cartRepo.addToCart(cartItem)
    }

    override fun deleteFromCart(cartItem: CartItem) {
        viewModel.removeFromCart(cartItem)
    }


}