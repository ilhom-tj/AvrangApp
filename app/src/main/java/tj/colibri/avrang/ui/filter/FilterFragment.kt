package tj.colibri.avrang.ui.filter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.slider.RangeSlider
import kotlinx.android.synthetic.main.filter_container_checbox.*
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.android.synthetic.main.filter_value_selector.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.FilterAttributeContainerAdapter
import tj.colibri.avrang.adapters.FilterCheckBoxAdapter
import tj.colibri.avrang.models.Category.Attribute_values
import tj.colibri.avrang.models.Category.Children
import tj.colibri.avrang.models.Filter.FilterData
import tj.colibri.avrang.utils.Features
import kotlin.collections.ArrayList

class FilterFragment : Fragment(), RangeSlider.OnChangeListener, RangeSlider.OnSliderTouchListener,
    FilterCheckBoxAdapter.CategoryClick, FilterAttributeContainerAdapter.CategoryClick {

    private val args : FilterFragmentArgs by navArgs()
    private val childList = ArrayList<Children>()
    private var currentCategory2 = ArrayList<String>()
    private var attributesArr = ArrayList<String>()
    private var catList = ArrayList<Int>()
    private var attrList = ArrayList<Int>()
    private var pF = 0.0f
    private var pT = 0.0f
    private lateinit var filterData : FilterData

    companion object {
        fun newInstance() = FilterFragment()
        const val REQUEST_KEY = "FILTERING"
    }

    private lateinit var viewModel: FilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)

        val checkBoxAdapter = FilterCheckBoxAdapter(this,this)
        val attributeContainer = FilterAttributeContainerAdapter(this,this)

        // val tempList = args.filterData.currentCategories?.split(",")

        filterData = args.filterData
        priceFrom.setText(args.filterData.priceFrom.toString())
        priceTo.setText(args.filterData.priceTo.toString())


        pF = args.filterData.priceFrom!!
        pT = args.filterData.priceTo!!


        filter_price_slider.valueFrom = pF
        filter_price_slider.valueTo = pT + 1

        filter_price_slider.setValues(pF,pT + 1)
        filter_price_slider.addOnSliderTouchListener(this)

        val bundle = Bundle()
        filterData.pages = null
        filterData.sort = null
        filterData.filter = null
       filter_button.setOnClickListener {
           bundle.putParcelable("filterData",filterData)
           setFragmentResult(REQUEST_KEY,bundle)
            findNavController().navigateUp()
        }

        args.category.categories.forEach { parent ->
            toList(parent.children)
        }

        filter_attribute_recyclerview.layoutManager = GridLayoutManager(requireContext(),1)
        attributeContainer.setData(args.category.attributes)
        filter_attribute_recyclerview.adapter = attributeContainer

        checkBoxAdapter.setData(childList)
        filter_checkbox_recyclerview.layoutManager = GridLayoutManager(requireContext(),1)
        filter_checkbox_recyclerview.adapter = checkBoxAdapter
        filter_checkbox_recyclerview.setHasFixedSize(true)

    }
    private fun toList(children: List<Children>){
        children.forEach { child ->
            if (child.children.isNotEmpty()){
                childList.add(child)
            }else{
                childList.add(child)
            }
        }
    }

    override fun onValueChange(slider: RangeSlider, value: Float, fromUser: Boolean) {
        Log.e("Val",filter_price_slider.values.toString())
        priceFrom.setText( slider.valueFrom.toString())
        priceTo.setText(slider.valueTo.toString())
    }

    override fun onStartTrackingTouch(slider: RangeSlider) {
        val digits = slider.values
        priceFrom.setText(digits[0].toString())
        priceTo.setText(digits[1].toString())

    }

    override fun onStopTrackingTouch(slider: RangeSlider) {
        val digits = slider.values

        filterData.priceFrom = digits[0]
        filterData.priceTo = digits[1]

        priceFrom.setText(digits[0].toString())
        priceTo.setText(digits[1].toString())

        pF = digits[0]
        pT = digits[1]
    }

//    @SuppressLint("SetTextI18n")
//    fun search(){
//        filterData.pages = null
//        if (attributesArr.isEmpty()){
//            filterData.attributes = null
//        }
//        viewModel.searchFilter(filterData).observe(viewLifecycleOwner, Observer {
//            filter_button.text = "Показать ${it.products.links.count} товаров"
//        })
//    }

    override fun attributeClick(children: Attribute_values) {
        attributesArr.clear()
        attrList.add(children.id)
        attrList.forEach{
            attributesArr.add(it.toString())
        }
        filterData.attributes = Features().toCurrentCategories(attributesArr)
    }
    
    override fun removeAttributeClick(children: Attribute_values) {
        attributesArr.clear()
        attrList.remove(children.id)
        attrList.forEachIndexed{ _, item ->
            attributesArr.add(item.toString())
        }
        filterData.attributes = Features().toCurrentCategories(attributesArr)
    }

    override fun categoryClicked(children: Children) {
        currentCategory2.clear()
        catList.add(children.id)
        catList.forEach{
            currentCategory2.add(it.toString())
        }
        if (currentCategory2.isNotEmpty()){

        }
        Log.e("CATEGORIES" ,Features().toCurrentCategories(currentCategory2) )
        filterData.currentCategories = Features().toCurrentCategories(currentCategory2)
    }

    override fun categoryRemoved(children: Children) {
        currentCategory2.clear()
        catList.remove(children.id)

        catList.forEachIndexed{ _, item ->
            currentCategory2.add(item.toString())
        }
        filterData.currentCategories = Features().toCurrentCategories(currentCategory2)
    }

}