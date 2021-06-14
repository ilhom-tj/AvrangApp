package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import tj.colibri.avrang.R
import tj.colibri.avrang.models.Home.Partners
import tj.colibri.avrang.utils.Const


class PartnersAdapter(val context: Fragment, private val itemClick: ItemClick) :
    RecyclerView.Adapter<PartnersAdapter.SliderHolder>() {

    private var items = emptyList<Partners>()

    override fun getItemCount() = items.size

    @SuppressLint("InflateParams")
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
        holder.itemView.setOnClickListener {
            itemClick.PartnersClick(item)
        }
    }

    inner class SliderHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.partner_image)

        @SuppressLint("CheckResult")
        fun bind(item: Partners) {
            val url = Uri.parse(Const.image_url + item.image)

            val imageLoader = ImageLoader.Builder(context.requireContext())
                .componentRegistry {
                    add(SvgDecoder(context.requireContext()))
                }
                .build()
            Coil.setImageLoader(imageLoader)

            image.load(url)
            image.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }


    interface ItemClick {
        fun PartnersClick(partners: Partners)
    }

}

