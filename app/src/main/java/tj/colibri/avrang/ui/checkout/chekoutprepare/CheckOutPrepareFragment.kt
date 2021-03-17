package tj.colibri.avrang.ui.checkout.chekoutprepare

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tj.colibri.avrang.R

class CheckOutPrepareFragment : Fragment() {

    companion object {
        fun newInstance() = CheckOutPrepareFragment()
    }

    private lateinit var viewModel: CheckOutPrepareViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.check_out_prepare_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CheckOutPrepareViewModel::class.java)
        // TODO: Use the ViewModel
    }

}