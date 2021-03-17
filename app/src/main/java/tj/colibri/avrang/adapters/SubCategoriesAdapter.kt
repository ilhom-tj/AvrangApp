package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.categories.SubCategory
import tj.colibri.avrang.data.product.options.Option
import tj.colibri.avrang.data.slider.SliderItem

class SubCategoriesAdapter(val fragment: Fragment, private val itemClickListener: ItemClicked) : RecyclerView.Adapter<SubCategoriesAdapter.SubCategoryHolder>() {

    private var items = emptyList<SubCategory>()

    override fun getItemCount()=items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoriesAdapter.SubCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_subcategory_item, null)
        return SubCategoryHolder(view)
    }

    fun setData(items: List<SubCategory>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SubCategoriesAdapter.SubCategoryHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class SubCategoryHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.subcategory_title)
        var layout : ConstraintLayout = view.findViewById(R.id.subcategory_layout)
        fun bind(item: SubCategory) {
            title.text = item.title
            layout.setOnClickListener {
                itemClickListener.onSubCategoryClicked(item)
            }
        }
    }

    interface ItemClicked {
        fun onSubCategoryClicked(item: SubCategory)
    }

}