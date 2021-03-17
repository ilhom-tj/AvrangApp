package tj.colibri.avrang.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.cart_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CartItemsAdapter
import tj.colibri.avrang.adapters.CommentAdapter
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.mock.MockData

class CartFragment : Fragment(), CartItemsAdapter.CartItemCliked {

    private lateinit var viewModel: CartViewModel
    private lateinit var cartItemsAdapter: CartItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cartItemsAdapter = CartItemsAdapter(this,this)

        cart_recycler_view.adapter = cartItemsAdapter
        cart_recycler_view.layoutManager = GridLayoutManager(context, 1,  LinearLayoutManager.VERTICAL, false)


        viewModel.getCartItems.observe(viewLifecycleOwner, Observer {
            it.let {
                cartItemsAdapter.setData(it)
            }
        })
        viewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it != null){
                    cart_total.setText(it.toString() + " TJS")
                }else{
                    cart_total.setText("0 TJS")
                }
            }

        })

        cart_check_out.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_cartFragment_to_checkOutFragment)
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