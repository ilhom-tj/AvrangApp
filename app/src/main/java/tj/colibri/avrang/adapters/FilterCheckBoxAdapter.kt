package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.filter_checkbox.FilterCheckBox

class FilterCheckBoxAdapter(val context : Fragment) : RecyclerView.Adapter<FilterCheckBoxAdapter.ProductHolder>() {

    private var checkBoxes = emptyList<FilterCheckBox>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.filter_checkbox_item, null)
        return ProductHolder(view)
    }

    fun setData(items: List<FilterCheckBox>) {
        this.checkBoxes = items
        notifyDataSetChanged()
    }

    override fun getItemCount()=checkBoxes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val checkers = checkBoxes[position]
        holder.checkBox.text = checkers.title
        holder.quantity.text = "(${checkers.quantity})"
    }

    inner class ProductHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var checkBox : CheckBox = view.findViewById(R.id.check_box)
        var quantity : TextView = view.findViewById(R.id.check_box_quantity)

    }

}