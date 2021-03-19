package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.order.OrderContainer
import tj.colibri.avrang.utils.ExpandMethods

class OrderContainerAdapter(val context : Fragment) : RecyclerView.Adapter<OrderContainerAdapter.ProductHolder>() {

    private var containers = emptyList<OrderContainer>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.order_container_layout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<OrderContainer>) {
        this.containers = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=containers.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = containers[position]
        holder.orderNumber.text = product.orderNumber.toString();
        holder.date.text = product.orderDate

        var quantity : Int = 0;
        var totalPrice : Double = 0.0;
        var bonus : Int = 0;
//
//        for (order in product.oreders){
//            quantity += order.quantity
//            totalPrice += order.quantity * order.unit_price
//            bonus += order.bonus
//        }

        holder.orderQuantity.text = quantity.toString()
        holder.allBonuses.text = "$bonus балов"
        holder.status.text = product.status
        holder.totalPrice.text = "$totalPrice TJS"

        var orderItemAdapter = OrderItemAdapter(context)
        holder.orderItem.adapter = orderItemAdapter
        holder.orderItem.layoutManager = LinearLayoutManager(context.requireContext())
        orderItemAdapter.setData(product.oreders)

        holder.expand.setOnClickListener{
            ExpandMethods().expand(holder.orderItem,500,holder.expand)

        }
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var orderNumber : TextView = view.findViewById(R.id.order_number)
        var date : TextView = view.findViewById(R.id.order_day)
        var orderQuantity : TextView = view.findViewById(R.id.fragment_order_recyclerview)
        var allBonuses : TextView = view.findViewById(R.id.all_bonuses)
        var totalPrice : TextView = view.findViewById(R.id.all_total_price)
        var status : TextView = view.findViewById(R.id.status)

        var orderItem : RecyclerView = view.findViewById(R.id.my_orders_cart)
        var expand : ImageView = view.findViewById(R.id.expandButton)


    }

//    interface ItemClicked {
//        fun onItemClicked(item: CartItem)
//    }

}