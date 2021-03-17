package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.home.Partners
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.utils.Const

class PartnersAdapter(val context: Fragment) : RecyclerView.Adapter<PartnersAdapter.SliderHolder>() {

    private var items = emptyList<Partners>()

    override fun getItemCount()=items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnersAdapter.SliderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.partner_item_layout, null)
        return SliderHolder(view)
    }

    fun setData(items: List<Partners>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PartnersAdapter.SliderHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class SliderHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.partner_image)
        fun bind(item: Partners) {
            // Временно
            Glide.with(context).load("https://www.apple.com/newsroom/images/values/corporate/Apple_google-partner-on-covid-19-contact-tracing-technology_04102020_LP_hero.jpg.og.jpg").into(image)
        }
    }

}