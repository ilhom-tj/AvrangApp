package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
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
import tj.colibri.avrang.utils.Features
import java.text.DecimalFormat
import java.text.SimpleDateFormat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class OrderContainerAdapter(val context: Fragment) :
    RecyclerView.Adapter<OrderContainerAdapter.ProductHolder>() {

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

    override fun getItemCount() = containers.size

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = containers[position]
        holder.orderNumber.text = product.id.toString()

        holder.date.text = Features().getNormalDate(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .parse(product.created_at)
        )

        var quantity: Int = 0;
        var totalPrice: Double = 0.0;
        var bonus: Int = 0;


        product.products.forEach {
            totalPrice += it.pivot.price * it.pivot.quantity
            bonus += it.bonus
        }

        holder.orderQuantity.text = product.products.size.toString()
        holder.status.text = product.status.name
        holder.totalPrice.text = "${DecimalFormat("##.##").format(totalPrice)} TJS"
        holder.allBonuses.text = "$bonus балов"


        var orderItemAdapter = OrderItemAdapter(context)
        holder.orderItem.adapter = orderItemAdapter
        holder.orderItem.layoutManager = LinearLayoutManager(context.requireContext())
        orderItemAdapter.setData(product.products)

        holder.expand.setOnClickListener {
            ExpandMethods().expand(holder.orderItem, 500, holder.expand)
        }
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var orderNumber: TextView = view.findViewById(R.id.order_number)
        var date: TextView = view.findViewById(R.id.order_day)
        var orderQuantity: TextView = view.findViewById(R.id.fragment_order_recyclerview)
        var allBonuses: TextView = view.findViewById(R.id.all_bonuses)
        var totalPrice: TextView = view.findViewById(R.id.all_total_price)
        var status: TextView = view.findViewById(R.id.status)

        var orderItem: RecyclerView = view.findViewById(R.id.my_orders_cart)
        var expand: ImageView = view.findViewById(R.id.expandButton)


    }

}