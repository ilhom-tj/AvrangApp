package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.Category.Children
import tj.colibri.avrang.utils.Const

class CatalogAdapter(val context : Fragment, private val itemClickListener: ItemClicked) : RecyclerView.Adapter<CatalogAdapter.CatalogHolder>() {

    private var items = emptyList<Children>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatalogAdapter.CatalogHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_catalog, null)
        return CatalogHolder(view)
    }

    fun setData(items: List<Children>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=items.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CatalogAdapter.CatalogHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.name

        val imageLoader = ImageLoader.Builder(context.requireContext())
            .componentRegistry {
                add(SvgDecoder(context.requireContext()))
            }
            .build()
        Coil.setImageLoader(imageLoader)
        holder.image.load(Const.image_url + item.icon)

        holder.layout.setOnClickListener {
            itemClickListener.onItemClicked(item)
        }
    }

    inner class CatalogHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var layout : ConstraintLayout = view.findViewById(R.id.catalog_layout)
        var image : ImageView = view.findViewById(R.id.catalog_image)
        var title: TextView = view.findViewById(R.id.catalog_title)


    }

    interface ItemClicked {
        fun onItemClicked(product: Children)
    }

}