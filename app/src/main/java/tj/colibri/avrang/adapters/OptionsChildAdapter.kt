package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.Product.options.Option

class OptionsChildAdapter(val fragment: Fragment) : RecyclerView.Adapter<OptionsChildAdapter.OptionHolder>() {

    private var items = emptyList<Option>()

    override fun getItemCount()=items.size

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsChildAdapter.OptionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.product_option_child_item, null)
        return OptionHolder(view)
    }

    fun setData(items: List<Option>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: OptionsChildAdapter.OptionHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class OptionHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.option_title)
        fun bind(item: Option) {
            title.text = item.title
        }
    }

}