package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import tj.colibri.avrang.R
import tj.colibri.avrang.models.Category.Children
import tj.colibri.avrang.utils.ExpandMethods

class FilterCheckBoxAdapter(val context: Fragment, val click: CategoryClick) :
    RecyclerView.Adapter<FilterCheckBoxAdapter.ProductHolder>(),
    FilterCategoryCheckBoxAdapter.CategoryClick {
    private var checkBoxes = mutableListOf<Children>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.filter_checkbox_item, null)
        return ProductHolder(view)
    }

    fun setData(items: List<Children>) {
        this.checkBoxes.clear()
        this.checkBoxes = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = checkBoxes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val checkers = checkBoxes[position]
        var isvisible = true
        val adapter = FilterCategoryCheckBoxAdapter(context, this)
        if (checkers.children.isEmpty()) {
            Log.e(checkers.name, checkers.slug)
            val array : MutableList<Children> = ArrayList<Children>()
            array.add(checkers)
            adapter.add(checkers)
            holder.signle.text = checkers.name
            holder.signle.visibility = View.VISIBLE
            holder.signle.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if (isChecked) {
                        click.categoryClicked(checkers)
                    } else {
                        click.categoryRemoved(checkers)
                    }
                }

            })
            holder.title.visibility = View.GONE
            holder.recyclerView.visibility = View.GONE
        } else {
            holder.title.visibility = View.VISIBLE
            holder.title.text = checkers.name
            holder.title.setOnClickListener {
                if (isvisible) {
                    ExpandMethods().expand(holder.recyclerView)
                    isvisible = false
                } else {
                    ExpandMethods().collapse(holder.recyclerView)
                    isvisible = true
                }

            }
            holder.recyclerView.visibility = View.GONE
            adapter.setData(checkers.children)
        }

        adapter.setData(checkers.children)
        holder.recyclerView.layoutManager = GridLayoutManager(context.requireContext(), 1)
        holder.recyclerView.adapter = adapter
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.category_name)
        var recyclerView: RecyclerView = view.findViewById(R.id.category_recycler_view)
        var signle : MaterialCheckBox = view.findViewById(R.id.singleCheckBox)
    }

    interface CategoryClick {
        fun categoryClicked(children: Children)
        fun categoryRemoved(children: Children)
    }

    override fun attributeClick(children: Children) {
        click.categoryClicked(children)
    }

    override fun removeAttributeClick(children: Children) {
        click.categoryRemoved(children)
    }


}