package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.Category.Categories
import tj.colibri.avrang.data.catalog.CatalogItem
import tj.colibri.avrang.utils.Const

class CatalogAdapter(val context : Fragment, private val itemClickListener: ItemClicked) : RecyclerView.Adapter<CatalogAdapter.CatalogHolder>() {

    private var items = emptyList<Categories>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogAdapter.CatalogHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_catalog, null)
        return CatalogHolder(view)
    }

    fun setData(items: List<Categories>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=items.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CatalogAdapter.CatalogHolder, position: Int) {
        val item = items[position]
        Glide
            .with(context)
            .load(Const.image_url + item.icon)
            .centerCrop()
            .into(holder.image)
//            .into(holder.image)
        holder.title.text = item.name
        holder.layout.setOnClickListener {
            itemClickListener.onItemClicked(item)
        }
    }

    inner class CatalogHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var layout : ConstraintLayout = view.findViewById(R.id.catalog_layout)
        var image : ImageView = view.findViewById(R.id.catalog_image)
        var title: TextView = view.findViewById(R.id.catalog_title)
        var more : ImageView = view.findViewById(R.id.catalog_more)

    }

    interface ItemClicked {
        fun onItemClicked(product: Categories)
    }

}