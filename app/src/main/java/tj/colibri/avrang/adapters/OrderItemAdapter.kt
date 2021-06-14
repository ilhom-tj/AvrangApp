package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.order.Products
import tj.colibri.avrang.utils.Const

class OrderItemAdapter(val context : Fragment) : RecyclerView.Adapter<OrderItemAdapter.ProductHolder>() {

    private var products = emptyList<Products>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.orders_item_layout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<Products>) {
        this.products = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=products.size

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.name
        holder.code.text = "Код продукта: ${product.sKU}"
        holder.unit_price.text = product.pivot.price.toString() + " TJS"
        holder.quantity.text = product.pivot.quantity.toString()

        if (product.bonus < 2){
            holder.bonus.text = product.bonus.toString() + " бал"
        }else{
            holder.bonus.text = product.bonus.toString() + " балов"
        }

        holder.total_price.text = (product.pivot.quantity * product.pivot.price).toString() + " TJS"

        Glide.with(context).load(Const.image_url + product.images[0]).into(holder.image)
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_title)
        var code : TextView = view.findViewById(R.id.product_code)
        var unit_price : TextView = view.findViewById(R.id.unit_price)
        var total_price : TextView = view.findViewById(R.id.total_price)
        var bonus : TextView = view.findViewById(R.id.item_bonus)
        var quantity: TextView = view.findViewById(R.id.item_quantity)
        var image: ImageView = view.findViewById(R.id.product_image)
    }


}