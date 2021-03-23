package tj.colibri.avrang.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.product.Sliders
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.utils.Const


class SliderAdapter(val context: Fragment, private val itemClickListener: ItemClicked) : SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {

    private var items = emptyList<Sliders>()

    fun setData(items: List<Sliders>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.slider_view_holder, parent, false)
        return SliderAdapterViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val currentItem = items[position]
        viewHolder.bind(currentItem)
        viewHolder.itemView.setOnClickListener {
            itemClickListener.onSliderItemClicked(currentItem)
        }
    }

    inner class SliderAdapterViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {

        private val image = itemView?.findViewById<ImageView>(R.id.slider_image)

        fun bind(item: Sliders) {
            if (image != null) {
                Glide.with(context).load(Const.image_url+item.mob_image).into(image)
            }
        }
    }

    interface ItemClicked {
        fun onSliderItemClicked(item: Sliders)
    }
}

