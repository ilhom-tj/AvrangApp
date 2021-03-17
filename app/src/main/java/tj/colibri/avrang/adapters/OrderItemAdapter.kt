package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.order.OrderItem

class OrderItemAdapter(val context : Fragment) : RecyclerView.Adapter<OrderItemAdapter.ProductHolder>() {

    private var products = emptyList<OrderItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.orders_item_layout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<OrderItem>) {
        this.products = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=products.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.title
        holder.code.text = "Код продукта: ${product.code}"
        holder.unit_price.text = product.unit_price.toString() + " TJS"
        holder.quantity.text = product.quantity.toString()

        if (product.bonus < 2){
            holder.bonus.text = product.bonus.toString() + " бал"
        }else{
            holder.bonus.text = product.bonus.toString() + " балов"
        }

        holder.total_price.text = (product.quantity * product.unit_price).toString() + " TJS"

    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_title)
        var code : TextView = view.findViewById(R.id.product_code)
        var unit_price : TextView = view.findViewById(R.id.unit_price)
        var total_price : TextView = view.findViewById(R.id.total_price)

//        var removeBtn : ImageView = view.findViewById(R.id.remove_from_favorite)

        var bonus : TextView = view.findViewById(R.id.item_bonus)
        var quantity : TextView = view.findViewById(R.id.item_quantity)

    }

//    interface ItemClicked {
//        fun onItemClicked(item: CartItem)
//    }

}