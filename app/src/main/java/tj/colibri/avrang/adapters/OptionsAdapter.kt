package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.product.options.ProductOptions

class OptionsAdapter(val fragment: Fragment) : RecyclerView.Adapter<OptionsAdapter.OptionHolder>() {

    private var items = emptyList<ProductOptions>()
    private lateinit var optionsChildAdapter: OptionsChildAdapter

    override fun getItemCount()=items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsAdapter.OptionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.product_option_item, null)
        optionsChildAdapter = OptionsChildAdapter(fragment)
        return OptionHolder(view)
    }

    fun setData(items: List<ProductOptions>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: OptionsAdapter.OptionHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title

        val linearLayoutManager = LinearLayoutManager(fragment.context,  LinearLayoutManager.HORIZONTAL, false)
        holder.rv.apply {
            layoutManager = linearLayoutManager
            adapter = optionsChildAdapter
        }
        optionsChildAdapter.setData(item.options)
    }

    inner class OptionHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title : TextView = view.findViewById(R.id.option_name)
        var rv: RecyclerView = view.findViewById(R.id.rv_child)
    }

}