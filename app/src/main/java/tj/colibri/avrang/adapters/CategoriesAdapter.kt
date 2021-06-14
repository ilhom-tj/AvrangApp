package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
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
import tj.colibri.avrang.models.Category.Children

@Suppress("DEPRECATION")
class CategoriesAdapter(val fragment: Fragment, private val itemClickListener : ItemClicked) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>(), SubCategoriesAdapter.ItemClicked {

    private var items = emptyList<Children>()
    private lateinit var subCategoriesAdapter: SubCategoriesAdapter

    override fun getItemCount()=items.size

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row_category_item, null)
        subCategoriesAdapter = SubCategoriesAdapter(fragment, this)
        return CategoryHolder(view)
    }

    fun setData(items: List<Children>) {
        this.items = items
        notifyDataSetChanged()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name

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
        if (item.children.isEmpty()) {
            holder.layout.setOnClickListener {
                itemClickListener.onParentClicked(item)
            }
        }
        subCategoriesAdapter.setData(item.children)
    }

    inner class CategoryHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var layout : ConstraintLayout = view.findViewById(R.id.category_layout)
        var expandable: ConstraintLayout = view.findViewById(R.id.category_expandable)
        var title : TextView = view.findViewById(R.id.category_title)
        var arrow : ImageView = view.findViewById(R.id.category_arrow)
        var rv: RecyclerView = view.findViewById(R.id.category_recycler_view)
    }


    override fun onSubCategoryClicked(item: Children) {
        itemClickListener.onSubCategoryClicked(item)
    }

    interface ItemClicked {
        fun  onParentClicked(item : Children)
        fun onSubCategoryClicked(item: Children)
    }

}