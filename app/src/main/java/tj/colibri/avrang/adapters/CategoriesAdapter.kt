package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.categories.Category
import tj.colibri.avrang.data.categories.SubCategory
import tj.colibri.avrang.data.product.options.ProductOptions

class CategoriesAdapter(val fragment: Fragment, private val itemClickListener : ItemClicked) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>(), SubCategoriesAdapter.ItemClicked {

    private var items = emptyList<Category>()
    private lateinit var subCategoriesAdapter: SubCategoriesAdapter

    override fun getItemCount()=items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_category_item, null)
        subCategoriesAdapter = SubCategoriesAdapter(fragment, this)
        return CategoryHolder(view)
    }

    fun setData(items: List<Category>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title

        holder.layout.setOnClickListener {
            println(holder.expandable.isVisible)
            if(!holder.expandable.isVisible) {
                holder.expandable.isVisible = true
                holder.arrow.setImageDrawable(fragment.context?.resources?.getDrawable(R.drawable.ic_category_up))
            } else {
                holder.expandable.isVisible = false
                holder.arrow.setImageDrawable(fragment.context?.resources?.getDrawable(R.drawable.ic_category_down))
            }
        }

        val linearLayoutManager = GridLayoutManager(fragment.context, 1,  LinearLayoutManager.VERTICAL, false)
        holder.rv.apply {
            layoutManager = linearLayoutManager
            adapter = subCategoriesAdapter
        }
        subCategoriesAdapter.setData(item.subcategories)
    }

    inner class CategoryHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var layout : ConstraintLayout = view.findViewById(R.id.category_layout)
        var expandable: ConstraintLayout = view.findViewById(R.id.category_expandable)
        var title : TextView = view.findViewById(R.id.category_title)
        var arrow : ImageView = view.findViewById(R.id.category_arrow)
        var rv: RecyclerView = view.findViewById(R.id.category_recycler_view)
    }



    override fun onSubCategoryClicked(item: SubCategory) {
        itemClickListener.onSubCategoryClicked(item)
    }

    interface ItemClicked {
        fun onSubCategoryClicked(item: SubCategory)
    }

}