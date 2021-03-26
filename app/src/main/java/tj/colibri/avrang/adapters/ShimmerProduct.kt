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
class ShimmerProduct(val context : Fragment) : RecyclerView.Adapter<ShimmerProduct.ProductHolder>() {

    private var products = mutableListOf<Int>()
    init {
        products.add(1)
        products.add(2)
        products.add(3)
        products.add(4)
        products.add(5)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShimmerProduct.ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.layout_demo_grid, null)
        return ProductHolder(view)
    }


    override fun getItemCount() = 5

    override fun onBindViewHolder(holder: ShimmerProduct.ProductHolder, position: Int) {
        val product = products[position]
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {

    }




}