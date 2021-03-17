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
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.utils.Const

class PromoSliderAdapter(val context: Fragment) : RecyclerView.Adapter<PromoSliderAdapter.SliderHolder>() {

    private var items = emptyList<SliderItem>()

    override fun getItemCount()=items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PromoSliderAdapter.SliderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.promo_slider_layout, null)
        return SliderHolder(view)
    }

    fun setData(items: List<SliderItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PromoSliderAdapter.SliderHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class SliderHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.promo_slider_image)
        fun bind(item: SliderItem) {
            if (image != null) {
                Glide.with(context).load(Const.base_url + item.image).into(image)
            }
        }
    }

}