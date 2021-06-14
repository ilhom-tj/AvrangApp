package tj.colibri.avrang.ui.checkout

import tj.colibri.avrang.data.ApiData.chekout.forRequest.CheckOutResquest
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.check_out_bank_transaction.*
import kotlinx.android.synthetic.main.check_out_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import tj.colibri.avrang.databinding.CheckOutFragmentBinding
import tj.colibri.avrang.ui.cart.CartViewModel
import tj.colibri.avrang.utils.Features
import java.text.SimpleDateFormat
import java.util.*

class CheckOutFragment : Fragment() {

    var myCalendar: Calendar = Calendar.getInstance()
    private lateinit var viewModel: CheckOutViewModel

    private var totalPrice = 0.0
    private var delivery = 1
    private var pay_method = 1
    private var checkOutItems = listOf<CheckOutItem>()
    private lateinit var binding: CheckOutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.check_out_fragment, container, false)
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetUpUI()
        viewModel = ViewModelProvider(this).get(CheckOutViewModel::class.java)
        val cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        cartViewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            it.let {
                totalPrice = it
            }
        })

        cartViewModel.indexes.observe(viewLifecycleOwner, Observer {
            it.let {
                checkOutItems = it
            }
        })


        binding.viewmodel = viewModel



        checkout_card_push.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_checkOutFragment_to_checkOutReadyFragment)
        }

        viewModel.data.observe(viewLifecycleOwner, Observer {
            it.let {
                it.user.city_id?.let { it1 -> SetCity(it1) }
            }
        })

        val Bdate =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel()
            }

        checkout_time.setOnClickListener {
            DatePickerDialog(
                requireContext(), R.style.MyTimePickerDialogTheme, Bdate, myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        checkout_card_push.setOnClickListener {
            PushCheckOut()
        }

    }

    private fun SetCity(id: Int) {
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            it.let {
                it.forEach { city ->
                    if (city.id == id) {
                        checkout_city.setText(city.name)
                    }
                }
            }
        })
    }


    private fun SetUpUI() {
        self_method.setOnClickListener {
            onRadioButtonClicked(self_method)
        }
        delivery_method.setOnClickListener {
            onRadioButtonClicked(delivery_method)
        }

        dont_use_bonus_and_promo.setOnClickListener {
            onBonusAndPromoButtonClick(dont_use_bonus_and_promo)
        }
        use_bonus.setOnClickListener {
            onBonusAndPromoButtonClick(use_bonus)
        }
        use_promo.setOnClickListener {
            onBonusAndPromoButtonClick(use_promo)
        }
        checkout_fragment_delivery_method.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.basic_method -> {
                    delivery = 1
                }
                R.id.fast_method -> {
                    delivery = 2
                }
            }
        }

        radioGroup_pay_method.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.payment_cash -> {
                    pay_method = 1
                    Log.e("ID", checkedId.toString())
                    onPayMethodRadioButtonClick(payment_cash)
                }
                R.id.payment_bank_transaction -> {
                    pay_method = 2
                    Log.e("ID", checkedId.toString())
                    onPayMethodRadioButtonClick(payment_bank_transaction)
                }
            }

        }


    }


    private fun onRadioButtonClicked(view: RadioButton) {
        val checked = view.isChecked
        when (view.id) {
            R.id.self_method ->
                if (checked) {
                    method_self.visibility = View.VISIBLE
                    method_delivery.visibility = View.GONE
                }
            R.id.delivery_method ->
                if (checked) {
                    method_self.visibility = View.GONE
                    method_delivery.visibility = View.VISIBLE
                }
        }

    }

    private fun updateLabel() {
        val myFormat = "dd-MM-yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        checkout_time.setText(sdf.format(myCalendar.time))
    }

    private fun onPayMethodRadioButtonClick(view: RadioButton) {
        val checked = view.isChecked

        // Check which radio button was clicked
        when (view.id) {
            R.id.payment_cash ->
                if (checked) {
                    check_out_container_bank_transaction.visibility = View.GONE

                }
            R.id.payment_bank_transaction ->
                if (checked) {
                    check_out_container_bank_transaction.visibility = View.VISIBLE

                }
        }
    }

    private fun onBonusAndPromoButtonClick(view: RadioButton) {
        val checked = view.isChecked
        // Check which radio button was clicked
        when (view.id) {
            R.id.dont_use_bonus_and_promo ->
                if (checked) {
                    check_out_container_use_bonus.visibility = View.GONE
                    check_out_container_promo.visibility = View.GONE
                }
            R.id.use_bonus ->
                if (checked) {
                    check_out_container_use_bonus.visibility = View.VISIBLE
                    check_out_container_promo.visibility = View.GONE
                }
            R.id.use_promo ->
                if (checked) {
                    check_out_container_use_bonus.visibility = View.GONE
                    check_out_container_promo.visibility = View.VISIBLE
                }
        }
    }

    private fun PushCheckOut() {
        val checkOutResquest = CheckOutResquest(
            checkOutItems.toTypedArray(),
            totalPrice,
            "Test comment",
            1,
            pay_method,
            checkout_city.text.toString(),
            checkout_adres.text.toString(),
            checkout_time.text.toString(),
            checkout_directions.text.toString(),
            0,
            delivery
        )
        val action = CheckOutFragmentDirections
            .actionCheckOutFragmentToCheckOutReadyFragment(checkOutResquest)
        Features().hideKeyboard(requireActivity())
        findNavController().navigate(action)
    }
}