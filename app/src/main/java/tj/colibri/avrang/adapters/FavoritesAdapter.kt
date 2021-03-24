package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.utils.Const

class FavoritesAdapter(val context : Fragment,private val removeClick : RemoveClickListener) : RecyclerView.Adapter<FavoritesAdapter.ProductHolder>() {

    private var products = ArrayList<ProductCard2>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_favorite_item, null)
        return ProductHolder(view)
    }

    fun setData(items: ArrayList<ProductCard2>) {
        this.products = items
        notifyDataSetChanged()
    }


    override fun getItemCount()=products.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        Log.e("Position",position.toString())
        holder.title.text = product.name
        holder.code.text = "Код продукта: ${product.sKU}"

        if (product.newPrice.equals(0.0)){
            holder.price.text = product.productPrice.toString() + " TJS"
            holder.oldPrice.visibility = View.INVISIBLE
        }else{
            holder.price.text = product.newPrice.toString() + " TJS"
            holder.oldPrice.text = product.productPrice.toString() + " TJS"
        }

        holder.removeBtn.setOnClickListener {
            removeClick.removeClickListener(product)
            products.removeAt(position)
            notifyDataSetChanged()
        }
        Glide.with(context).load(Const.image_url + product.images.get(0)).into(holder.image)
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_title)
        var code : TextView = view.findViewById(R.id.product_title)
        var price : TextView = view.findViewById(R.id.product_price)
        var oldPrice : TextView = view.findViewById(R.id.product_old_price)
        var removeBtn : ImageView = view.findViewById(R.id.remove_from_favorite)
        var image : ImageView = view.findViewById(R.id.product_background)
    }

    interface RemoveClickListener {
        fun removeClickListener(favorite: ProductCard2)
    }

}