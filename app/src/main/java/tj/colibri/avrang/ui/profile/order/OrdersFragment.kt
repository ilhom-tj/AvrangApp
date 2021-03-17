package tj.colibri.avrang.ui.profile.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_orders.fragment_order_recyclerview
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.OrderContainerAdapter
import tj.colibri.avrang.data.mock.MockData

class OrdersFragment : Fragment() {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private lateinit var viewModel: OrdersViewModel
    private lateinit var orderContainerAdapter: OrderContainerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)
        // TODO: Use the ViewModel

        orderContainerAdapter = OrderContainerAdapter(this)
        orderContainerAdapter.setData(MockData.listofOrderContainer)


        fragment_order_recyclerview.apply {
            layoutManager = GridLayoutManager(requireContext(),1)
            adapter = orderContainerAdapter
        }




    }

}