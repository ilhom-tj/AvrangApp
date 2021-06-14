package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.Category.Children

class SubCategoriesAdapter(val fragment: Fragment, private val itemClickListener: ItemClicked) : RecyclerView.Adapter<SubCategoriesAdapter.SubCategoryHolder>() {

    private var items = emptyList<Children>()

    override fun getItemCount()=items.size

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoriesAdapter.SubCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_subcategory_item, null)
        return SubCategoryHolder(view)
    }

    fun setData(items: List<Children>) {
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
        fun bind(item: Children) {
            title.text = item.name
            layout.setOnClickListener {
                itemClickListener.onSubCategoryClicked(item)
            }
        }
    }

    interface ItemClicked {
        fun onSubCategoryClicked(item: Children)
    }

}