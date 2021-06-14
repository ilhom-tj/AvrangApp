package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.product.ProductInfo.Attributes

class SpecificationsAdapter(val fragment: Fragment) : RecyclerView.Adapter<SpecificationsAdapter.SpecificationHolder>() {

    private var items = emptyList<Attributes>()

    override fun getItemCount()=items.size

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecificationsAdapter.SpecificationHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.specifications_item, null)
        return SpecificationHolder(view)
    }

    fun setData(items: List<Attributes>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SpecificationsAdapter.SpecificationHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.value.text = item.value
    }

    inner class SpecificationHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var name : TextView = view.findViewById(R.id.specification_name)
        var value: TextView = view.findViewById(R.id.specification_value)
    }

}