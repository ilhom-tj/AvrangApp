package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.Category.Attribute_values
import tj.colibri.avrang.data.ApiData.Category.Attributes

class FilterAttributeContainerAdapter(val context: Fragment, private val itemClick: CategoryClick) :
    RecyclerView.Adapter<FilterAttributeContainerAdapter.ProductHolder>(),
    FilterAttributeCheckBoxAdapter.CategoryClick {

    private var checkBoxes = listOf<Attributes>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.filter_attribute_container, null)
        return ProductHolder(view)
    }

    fun setData(items: List<Attributes>) {
        this.checkBoxes = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = checkBoxes.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val container = checkBoxes[position]
        holder.title.text = container.name
         val attributesBoxAdapter = FilterAttributeCheckBoxAdapter(context, this)
        attributesBoxAdapter.setData(container.attribute_values)
        holder.recyclerView.layoutManager = GridLayoutManager(context.requireContext(), 1)
        holder.recyclerView.adapter = attributesBoxAdapter
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var recyclerView: RecyclerView = view.findViewById(R.id.attributes)
    }

    interface CategoryClick {
        fun attributeClick(children: Attribute_values)
        fun removeAttributeClick(children: Attribute_values)
    }

    override fun attributeClick(children: Attribute_values) {
        itemClick.attributeClick(children)
    }

    override fun removeAttributeClick(children: Attribute_values) {
        itemClick.removeAttributeClick(children)
    }

}