package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.Installment.Installment
import java.text.DecimalFormat

class InstDurationAdapter(val context: Context) : BaseAdapter() {


    private var data = emptyList<Installment>()
    private var totalPrice = 0.0

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return data.size
    }

    fun getData(position: Int): Installment {
        return data[position]
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(data: List<Installment>, totalPrice: Double) {
        this.data = data
        this.totalPrice = totalPrice
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.dedline_select_spinner_layout, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }

        val precent =
            ((totalPrice * data[position].percent / 100) + totalPrice) / data[position].months
        vh.label.text =
            "${data[position].months} мес x ${DecimalFormat("##.##").format(precent)} TJS"


        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.txt) as TextView
    }
}