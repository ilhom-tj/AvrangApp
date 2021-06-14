package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.favorite.FavoriteCard
import tj.colibri.avrang.utils.Const

class FavoritesAdapter(val context: Fragment, private val removeClick: ClickListener) :
    RecyclerView.Adapter<FavoritesAdapter.ProductHolder>() {

    private var products = ArrayList<FavoriteCard>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_favorite_item, null)
        return ProductHolder(view)
    }

    fun setData(items: ArrayList<FavoriteCard>) {
        this.products = items
        notifyDataSetChanged()
    }


    override fun getItemCount()=products.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]

        holder.title.text = product.name
        holder.code.text = "Код продукта: ${product.sku}"

        if (product.discounted.equals(0.0)){
            holder.price.text = product.price.toString() + " TJS"
            holder.oldPrice.visibility = View.INVISIBLE
        }else{
            holder.price.text = product.discounted.toString() + " TJS"
            holder.oldPrice.text = product.price.toString() + " TJS"
        }

        holder.oldPrice.paintFlags = holder.oldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


        holder.removeBtn.setOnClickListener {

            products.removeAt(position)
            holder.removeBtn.isClickable = false
            notifyDataSetChanged()
            removeClick.removeClickListener(product)

        }
        holder.itemView.setOnClickListener {
            removeClick.itemClickListener(product)
        }
        Glide.with(context).load(Const.image_url + product.image).into(holder.image)
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.product_title)
        var code: TextView = view.findViewById(R.id.product_code)
        var price: TextView = view.findViewById(R.id.product_price)
        var oldPrice: TextView = view.findViewById(R.id.product_old_price)
        var removeBtn: ImageView = view.findViewById(R.id.remove_from_favorite)
        var image: ImageView = view.findViewById(R.id.product_background)
    }

    interface ClickListener {
        fun itemClickListener(favorite: FavoriteCard)
        fun removeClickListener(favorite: FavoriteCard)
    }

}