package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.product.Banks
import tj.colibri.avrang.utils.Const

class BanksAdapter(val context: Fragment, private val itemClicked: ItemClicked) :
    RecyclerView.Adapter<BanksAdapter.BanksHolder>() {

    private var products = emptyList<Banks>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BanksHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.bank_select_spinner_layout, null)
        return BanksHolder(view)
    }

    fun setData(items: List<Banks>) {
        this.products = items
        notifyDataSetChanged()
    }


    override fun getItemCount()=products.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BanksHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.name

        val url = Uri.parse(Const.image_url + product.image)
        val imageLoader = ImageLoader.Builder(context.requireContext())
            .componentRegistry {
                add(SvgDecoder(context.requireContext()))
            }
            .build()
        Coil.setImageLoader(imageLoader)

        holder.img.load(url)
        holder.itemView.setOnClickListener {
            itemClicked.bankClick(product)
        }
        //   Glide.with(context).load(Const.image_url + product.image).into(holder.img)

    }

    inner class BanksHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var img: ImageView = view.findViewById(R.id.img)
        var title: TextView = view.findViewById(R.id.txt)

    }


    interface ItemClicked {

        fun bankClick(bank: Banks)

    }

}