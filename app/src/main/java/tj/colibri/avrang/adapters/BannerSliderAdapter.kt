package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.home.Banners
import tj.colibri.avrang.utils.Const

class BannerSliderAdapter(val context: Fragment) : RecyclerView.Adapter<BannerSliderAdapter.SliderHolder>() {

    private var items = emptyList<Banners>()

    override fun getItemCount()=items.size

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerSliderAdapter.SliderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.banner_slider_layout, null)
        return SliderHolder(view)
    }

    fun setData(items: List<Banners>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BannerSliderAdapter.SliderHolder, position: Int) {
        val item = items[position]
        Log.e("dsa", item.mob_image)
        Glide.with(context).load(Const.image_url + item.mob_image).into(holder.image)
    }

    inner class SliderHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.banner_image)
    }

}