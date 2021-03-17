package tj.colibri.avrang.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.favorite.Favorite

class FavoritesAdapter(val context : Fragment,private val removeClick : RemoveClickListener) : RecyclerView.Adapter<FavoritesAdapter.ProductHolder>() {

    private var products = ArrayList<Favorite>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_favorite_item, null)
        return ProductHolder(view)
    }

    fun setData(items: ArrayList<Favorite>) {
        this.products = items
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        Log.e("position",position.toString())
        this.products.removeAt(position)
        notifyItemChanged(position)
    }

    override fun getItemCount()=products.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        Log.e("Position",position.toString())
        holder.title.text = product.title
        holder.code.text = "Код продукта: ${product.code}"
        holder.price.text = product.price.toString()
        holder.oldPrice.text = product.oldPrice.toString()
        holder.removeBtn.setOnClickListener {
            removeClick.removeClickListener(product)
        }
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_title)
        var code : TextView = view.findViewById(R.id.product_title)
        var price : TextView = view.findViewById(R.id.product_price)
        var oldPrice : TextView = view.findViewById(R.id.product_old_price)
        var removeBtn : ImageView = view.findViewById(R.id.remove_from_favorite)
    }

    interface RemoveClickListener {
        fun removeClickListener(favorite: Favorite)
    }

}