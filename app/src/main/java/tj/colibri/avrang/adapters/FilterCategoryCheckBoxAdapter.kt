package tj.colibri.avrang.adapters


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.Category.Children

class FilterCategoryCheckBoxAdapter(val context: Fragment, private val itemClick: CategoryClick) :
    RecyclerView.Adapter<FilterCategoryCheckBoxAdapter.ProductHolder>() {

    private var checkBoxes = mutableListOf<Children>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.checkboxlayout, null)
        return ProductHolder(view)
    }

    fun setData(items: List<Children>) {
        this.checkBoxes.clear()
        this.checkBoxes = items.toMutableList()
        notifyDataSetChanged()
    }
    fun add(items: Children) {
        this.checkBoxes.clear()
        this.checkBoxes.add(items)
        notifyItemInserted(0)
    }

    override fun getItemCount() = checkBoxes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val checkers = checkBoxes[position]
        Log.e("ADDD",checkers.name)
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
        fun attributeClick(children: Children)
        fun removeAttributeClick(children: Children)
    }

}