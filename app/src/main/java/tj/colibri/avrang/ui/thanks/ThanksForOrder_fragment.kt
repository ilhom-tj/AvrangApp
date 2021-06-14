package tj.colibri.avrang.ui.thanks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tj.colibri.avrang.R

class ThanksForOrder_fragment : Fragment() {

    companion object {
        fun newInstance() = ThanksForOrder_fragment()
    }

    private lateinit var viewModel: ThanksForOrderFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thanks_for_order_fragment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThanksForOrderFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}