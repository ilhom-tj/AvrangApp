package tj.colibri.avrang.ui.FAQ

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.faq_fragment.*
import tj.colibri.avrang.R

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
        faq_payment_delivery.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_FAQFragment_to_infoContainerFragment)
        }
    }

}