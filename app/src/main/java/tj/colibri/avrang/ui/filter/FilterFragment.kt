package tj.colibri.avrang.ui.filter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.filter_container_checbox.*
import kotlinx.android.synthetic.main.filter_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.FilterCheckBoxAdapter
import tj.colibri.avrang.data.mock.MockData

class FilterFragment : Fragment() {

    companion object {
        fun newInstance() = FilterFragment()
    }

    private lateinit var viewModel: FilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)
        // TODO: Use the ViewModel
        val checkBoxAdapter = FilterCheckBoxAdapter(this)
        checkBoxAdapter.setData(MockData.listofCheckBoxes)
        filter_checkbox_recyclerview.layoutManager = GridLayoutManager(requireContext(),1)
        filter_checkbox_recyclerview.adapter = checkBoxAdapter
    }

}