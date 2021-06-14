package tj.colibri.avrang.ui.FAQ

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.faq_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.FAQAdapter

class FAQFragment : Fragment() {

    companion object {
        fun newInstance() = FAQFragment()
    }

    private lateinit var viewModel: FAQViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.faq_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FAQViewModel::class.java)

        val faqAdapter = FAQAdapter(this)

        faq_recyclerview.layoutManager = GridLayoutManager(requireContext(),1)

        faq_recyclerview.adapter = faqAdapter

        viewModel.getAllFAQs()

        viewModel.FAQList.observe(viewLifecycleOwner, Observer {
            it.let {
                faqAdapter.setData(it)
            }
        })


    }

}