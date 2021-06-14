package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import tj.colibri.avrang.R
import tj.colibri.avrang.models.FAQ.FAQs
import tj.colibri.avrang.utils.ExpandMethods

class FAQAdapter(val context: Fragment) : RecyclerView.Adapter<FAQAdapter.FAQHolder>() {

    private var products = emptyList<FAQs>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FAQHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.faq_item, null)
        return FAQHolder(view)
    }

    fun setData(items: List<FAQs>) {
        this.products = items
        notifyDataSetChanged()
    }


    override fun getItemCount() = products.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FAQHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.question
        holder.answer.text = product.answer
        holder.itemView.setOnClickListener {
            if (holder.answer.visibility == View.GONE) {
                ExpandMethods().expand(holder.answer)
            } else {
                ExpandMethods().collapse(holder.answer)
            }
        }
    }

    inner class FAQHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.question)
        var answer: TextView = view.findViewById(R.id.answer)
    }


}