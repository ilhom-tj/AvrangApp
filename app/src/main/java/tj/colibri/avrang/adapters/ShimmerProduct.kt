package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R

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

    @SuppressLint("InflateParams")
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
        //val product = products[position]
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view)


}