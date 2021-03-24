package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_info_fragment_v2.*
import tj.colibri.avrang.R
import tj.colibri.avrang.data.comments.Comment
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ProductCardAdapter(val context : Fragment,
                         private val itemClickListener: ItemClicked
                         ) : RecyclerView.Adapter<ProductCardAdapter.ProductHolder>() {

    private var products = emptyList<ProductCard2>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCardAdapter.ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.product_layout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<ProductCard2>) {
        this.products = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=products.size

    override fun onBindViewHolder(holder: ProductCardAdapter.ProductHolder, position: Int) {
        val product = products[position]
        holder.product_title.text = product.name
        if (product.isFavorite){
            holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

         Glide
            .with(context)
            .load(Const.image_url + product.images.get(0))
            .fitCenter()
            .into(holder.product_image)
//        holder.bestseller.isVisible = product.labels.is_hit
        holder.product_rating.text = "${product.rating.rating}"
        holder.ratings_qty.text = "${product.rating.quantity} отзывов"


        Log.e("price", product.productPrice.toString())
        Log.e("newPrice",product.newPrice.toString())
        if(product.newPrice.equals(0.0)){
            holder.product_old_price.visibility = View.INVISIBLE
            holder.product_price.text = "${product.productPrice}  TJS"
        }else{
            holder.product_old_price.visibility = View.VISIBLE
            holder.product_old_price.text = "${product.productPrice}"
            holder.product_price.text = "${product.newPrice}  TJS"
            holder.product_old_price.setPaintFlags(holder.product_old_price.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }



        holder.product_card.setOnClickListener {
            itemClickListener.onProductItemClicked(product)
        }
        holder.product_favorite.setOnClickListener {
            if (product.isFavorite){
                holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                products.get(position).isFavorite = false
                notifyDataSetChanged()
                itemClickListener.onRemoveClickListener(product)
            }else{
                holder.product_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                products.get(position).isFavorite = true
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




//    @SuppressLint("SimpleDateFormat")
//    fun sortByDate(){
//        Collections.sort(products, Comparator<ProductCard>{
//            product1,product2 ->
////            Log.e("date",product1.labels.)
////            val Date1 : Date = product1.productDate!!
////            val Date2 : Date = product2.productDate!!
////            Date1.time.compareTo(Date2.time)
////        })
//        notifyDataSetChanged()
//    }
//    fun sortBySaleIndex(){
//        Collections.sort(
//            products,
//            Comparator<ProductCard> { prd1, prd2 -> // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                if (prd1.productSaleIndex > prd2.productSaleIndex) -1 else if (prd1.productSaleIndex < prd2.productSaleIndex) 1 else 0
//            })
//        notifyDataSetChanged()
//    }
//    fun sortByRating(){
//
//    }
//    fun sortByLowPrice(){
//        Collections.sort(
//            products,
//            Comparator<ProductCard> { prd1, prd2 -> // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                if (prd1.productPrice < prd2.productPrice) -1 else if (prd1.productPrice > prd2.productPrice) 1 else 0
//            })
//        notifyDataSetChanged()
//    }
//    fun sortByHightPrice(){
//        Collections.sort(
//            products,
//            Comparator<ProductCard> { prd1, prd2 -> // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                if (prd1.productPrice > prd2.productPrice) -1 else if (prd1.productPrice < prd2.productPrice) 1 else 0
//            })
//        notifyDataSetChanged()
//    }
//    fun sortByDiscount(){
//        Collections.sort(
//            products,
//            Comparator<ProductCard> { prd1, prd2 -> // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                if (prd1.productDiscount > prd2.productDiscount) -1 else if (prd1.productDiscount < prd2.productDiscount) 1 else 0
//            })
//        notifyDataSetChanged()
//    }
}