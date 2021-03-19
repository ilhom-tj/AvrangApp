package tj.colibri.avrang.ui.cart

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.cart_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CartItemsAdapter
import tj.colibri.avrang.adapters.CommentAdapter
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.mock.MockData
import tj.colibri.avrang.data.order.OrderItem
import tj.colibri.avrang.utils.SessionManager
import java.text.DecimalFormat

class CartFragment : Fragment(), CartItemsAdapter.CartItemCliked {

    private lateinit var viewModel: CartViewModel
    private lateinit var cartItemsAdapter: CartItemsAdapter
    private var orderList : ArrayList<OrderItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cartItemsAdapter = CartItemsAdapter(this,this)

        cart_recycler_view.adapter = cartItemsAdapter
        cart_recycler_view.layoutManager = GridLayoutManager(context, 1,  LinearLayoutManager.VERTICAL, false)


        viewModel.getCartItems.observe(viewLifecycleOwner, Observer {
            it.let {
                cartItemsAdapter.setData(it)
                it.forEach { item ->
                    orderList.add(OrderItem(item.id,item.quantity))
                }
            }
        })
        viewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it != null){
                    cart_total.setText(DecimalFormat("##.##").format(it) + " TJS")
                }else{
                    cart_total.setText("0 TJS")
                }
            }

        })

        cart_check_out.setOnClickListener{
            if (SessionManager(requireContext()).getToken() != "error"){
                val action = CartFragmentDirections.actionCartFragmentToCheckOutFragment(orderList.toTypedArray())
                findNavController().navigate(action)
            }else{
                findNavController().navigate(R.id.loginFragment)
            }

        }


    }

    override fun onPlusQuantityClick(cartItem: CartItem, quantity: Int) {
        var cartItemUpdate = cartItem
        cartItemUpdate.quantity = quantity
        viewModel.cartRepo.addToCart(cartItemUpdate)
    }

    override fun onMinusQuantityClick(cartItem: CartItem, quantity: Int) {
        var cartItemUpdate = cartItem
        cartItemUpdate.quantity = quantity
        viewModel.cartRepo.addToCart(cartItemUpdate)
    }

    override fun deleteFromCart(cartItem: CartItem) {
        viewModel.removeFromCart(cartItem)
    }


}