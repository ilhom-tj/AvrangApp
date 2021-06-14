package tj.colibri.avrang.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.Coil
import coil.load
import tj.colibri.avrang.R
import tj.colibri.avrang.models.Installment.Banks
import tj.colibri.avrang.models.Installment.Installment
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.ImageLoader

class BankSpinnerAdapter(val context: Context) : BaseAdapter() {

    private var data = emptyList<Banks>()

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(data: List<Banks>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.bank_select_spinner_item, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = data[position].name

        Coil.setImageLoader(ImageLoader(context).getImageLoader())

        val uri = Uri.parse(Const.image_url + data[position].image)
        vh.img.load(uri)


        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.txt) as TextView
        val img: ImageView = row?.findViewById(R.id.img) as ImageView

    }

    fun getData(position: Int): List<Installment> {
        return data[position].installments
    }
}