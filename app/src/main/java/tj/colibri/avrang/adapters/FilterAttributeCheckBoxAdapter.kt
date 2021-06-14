package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.models.Category.Attribute_values

class FilterAttributeCheckBoxAdapter(val context: Fragment, private val itemClick: CategoryClick) :
    RecyclerView.Adapter<FilterAttributeCheckBoxAdapter.ProductHolder>() {

    private var checkBoxes = mutableListOf<Attribute_values>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.checkboxlayout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<Attribute_values>) {
        this.checkBoxes.clear()
        this.checkBoxes = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = checkBoxes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val checkers = checkBoxes[position]
        holder.checkBox.text = checkers.name
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                itemClick.attributeClick(checkers)

            } else {
                itemClick.removeAttributeClick(checkers)

            }
        }
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var checkBox: CheckBox = view.findViewById(R.id.checkbox)
    }

    interface CategoryClick {
        fun attributeClick(children: Attribute_values)
        fun removeAttributeClick(children: Attribute_values)
    }

}