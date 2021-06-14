package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.product.ProductCard2
import tj.colibri.avrang.utils.Const

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ProductCardAdapter(val context : Fragment,
                         private val itemClickListener: ItemClicked
                         ) : RecyclerView.Adapter<ProductCardAdapter.ProductHolder>() {

    var products = mutableListOf<ProductCard2>()


    fun removeAll() {
        products.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCardAdapter.ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.product_layout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<ProductCard2>) {
        this.products = items.toMutableList()
        notifyDataSetChanged()
    }

    fun addMoreProducts(items : List<ProductCard2>){
        this.products.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount()=products.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductCardAdapter.ProductHolder, position: Int) {
        val product = products[position]
        holder.product_title.text = product.name

        if (product.isFavorite){
            holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            holder.product_favorite.setColorFilter(ContextCompat.getColor(context.requireContext(),R.color.red_primary))
        }else{
            holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            holder.product_favorite.clearColorFilter()
        }

        Glide
            .with(context)
            .load(Const.image_url + product.images[0])
            .fitCenter()
            .into(holder.product_image)
//        holder.bestseller.isVisible = product.labels.is_hit
        holder.product_rating.text = "${product.rating.rating}"
        holder.ratings_qty.text = "${product.rating.quantity} отзывов"


        if (product.newPrice.equals(0.0)) {
            holder.product_old_price.visibility = View.INVISIBLE
            holder.product_price.text = "${product.productPrice}  TJS"
        } else {
            holder.product_old_price.visibility = View.VISIBLE
            holder.product_old_price.text = "${product.productPrice}"
            holder.product_price.text = "${product.newPrice}  TJS"
            holder.product_old_price.paintFlags =
                holder.product_old_price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        if (product.labels.is_promotion) {
            holder.sale.visibility = View.VISIBLE
        }
        if (product.labels.is_hit) {
            holder.bestseller.visibility = View.VISIBLE
        }



        holder.product_card.setOnClickListener {
            itemClickListener.onProductItemClicked(product)
        }

        holder.product_favorite.setOnClickListener {
            if (product.isFavorite) {
                holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                holder.product_favorite.clearColorFilter()
             //   holder.product_favorite.setColorFilter(ContextCompat.getColor(context.requireContext(),R.color.red_primary))
                products[position].isFavorite = false
                notifyDataSetChanged()
                itemClickListener.onRemoveClickListener(product)
            }else{
                holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                holder.product_favorite.setColorFilter(ContextCompat.getColor(context.requireContext(),R.color.red_primary))
                products[position].isFavorite = true
                notifyDataSetChanged()
                itemClickListener.onAddProductToFavorite(product)
            }

        }
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var product_card : CardView = view.findViewById(R.id.product_card)
        var product_title: TextView = view.findViewById(R.id.product_title)
        var sale : TextView = view.findViewById(R.id.product_sale)
        var bestseller : TextView = view.findViewById(R.id.product_bestseller)
        var product_favorite : ImageView = view.findViewById(R.id.product_favorite)
        var product_image : ImageView = view.findViewById(R.id.product_image)
        var product_rating : TextView = view.findViewById(R.id.product_rating)
        var ratings_qty : TextView = view.findViewById(R.id.ratings_qty)
        var product_old_price : TextView = view.findViewById(R.id.product_old_price)
        var product_price : TextView = view.findViewById(R.id.product_price)

    }

    interface ItemClicked {
        fun onProductItemClicked(product: ProductCard2)
        fun onAddProductToFavorite(favorite: ProductCard2)
        fun onRemoveClickListener(favorite: ProductCard2)
    }
}