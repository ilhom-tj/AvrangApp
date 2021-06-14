package tj.colibri.avrang.ui.FAQ.infoContainer

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.faq_container_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.utils.Const
import java.text.SimpleDateFormat
import java.util.*


class InfoContainerFragment : Fragment() {

    private val args : InfoContainerFragmentArgs by navArgs()
    companion object {
        fun newInstance() = InfoContainerFragment()
    }

    private lateinit var viewModel: InfoContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.faq_container_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoContainerViewModel::class.java)
        Glide.with(requireActivity()).load(Const.image_url + args.bannerURL).into(banner)


        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = inputFormat.parse(args.createdAt)
        val formattedDate = outputFormat.format(date)



        created_at.text = toDate(formattedDate)
        web_text.text = Html.fromHtml(args.description)
    }

    fun toDate(date : String) : String{
        var newDate : String
        newDate = date.split("-")[0] + " " +
                month(date.split("-")[1].toInt()) + " " +
                date.split("-")[2]
        return newDate
    }

    fun month(index : Int) : String{
        var month : String
        if (index == 1)
            return "января"
        if (index == 2)
            return "февраля"
        if (index == 3)
            return "марта"
        if (index == 4)
            return "апреля"
        if (index == 5)
            return "мая"
        if (index == 6)
            return "июня"
        if (index == 7)
            return "июля"
        if (index == 8)
            return "августа"
        if (index == 9)
            return "сентября"
        if (index == 10)
            return "октября"
        if (index == 11)
            return "ноября"
        if (index == 12)
            return "декабря"
        return "неизвестно"
    }
}