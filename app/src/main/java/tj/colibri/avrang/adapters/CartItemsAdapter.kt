package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features
import java.util.*

class CartItemsAdapter(
    val context: Fragment,
    private val itemClick: CartItemCliked
) : RecyclerView.Adapter<CartItemsAdapter.ProductHolder>() {

    private var products = mutableListOf<CartItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_cart_item, null)
        return ProductHolder(view)
    }

    fun setData(items: MutableList<CartItem>) {
        this.products = items
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        products.removeAt(position)
        notifyDataSetChanged()
    }


    override fun getItemCount() = products.size

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.name
        holder.code.text = "Код продукта: " + product.sku
        holder.quantity.text = product.quantity.toString()

        holder.unit_price.text = product.price.toString()
        holder.total_price.text = (product.price * product.quantity).toString() + " TJS"

        holder.quantity.setText(product.quantity.toString())

        holder.removeBtn.setOnClickListener {
            itemClick.deleteFromCart(product)
        }

        holder.minusBtn.setOnClickListener {
            if (holder.quantity.text.toString().toInt() > 0) {

                holder.quantity.text = (holder.quantity.text.toString().toInt() - 1).toString()
                var qty = Integer.parseInt(holder.quantity.text.toString())
                holder.total_price.text =
                    (product.price * holder.quantity.text.toString().toInt()).toString() + " TJS"

                itemClick.onMinusQuantityClick(product,qty)
            }

        }
        holder.plusBtn.setOnClickListener {
            if (holder.quantity.text.toString().toInt() < product.in_stock){
            holder.quantity.text = (holder.quantity.text.toString().toInt() + 1).toString()
            var qty = Integer.parseInt(holder.quantity.text.toString())

            holder.total_price.text =
                (product.price * holder.quantity.text.toString().toInt()).toString() + " TJS"
            itemClick.onPlusQuantityClick(product,qty)
            }
        }
        holder.bonus.setText(product.bonus.toString() + " Баллов")


        Glide.with(context).load(Const.image_url + product.images).into(holder.image)

    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_title)
        var code: TextView = view.findViewById(R.id.product_code)
        var unit_price: TextView = view.findViewById(R.id.unit_price)
        var total_price: TextView = view.findViewById(R.id.total_price)
        var removeBtn: ImageView = view.findViewById(R.id.remove_from_favorite)
        var minusBtn: MaterialCardView = view.findViewById(R.id.qty_minus)
        var plusBtn: MaterialCardView = view.findViewById(R.id.qty_plus)
        var quantity: TextView = view.findViewById(R.id.qty)
        var bonus : TextView = view.findViewById(R.id.bonuses)
        var image : ImageView = view.findViewById(R.id.product_background)
    }

    interface CartItemCliked {
        fun onPlusQuantityClick(cartItem: CartItem,quantity : Int)
        fun onMinusQuantityClick(cartItem: CartItem,quantity : Int)
        fun deleteFromCart(cartItem: CartItem)
    }
}