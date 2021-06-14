package tj.colibri.avrang.ui.checkout.installation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.check_out_installation.*
import kotlinx.android.synthetic.main.instalation_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.*
import tj.colibri.avrang.data.ApiData.Installment.PayClass
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem
import java.util.*


class InstalationFragment : Fragment() {


    private lateinit var banksAdapter: BankSpinnerAdapter
    private lateinit var instDurationAdapter: InstDurationAdapter
    private lateinit var bundle: Bundle
    private var quickProduct = 0
    private var bankID = 0

    companion object {
        fun newInstance() = InstalationFragment()
    }

    private lateinit var viewModel: InstalationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.instalation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val installment = PayClass(emptyList(), "empty", "empty", 1)
        viewModel = ViewModelProvider(this).get(InstalationViewModel::class.java)

        var totalPrice = 0.0
        //    Log.e("TOTAL", totalPrice.toString())


        viewModel.totalPrice.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it != null) {
                    totalPrice = it
                }
            }
        })
        instDurationAdapter = InstDurationAdapter(this.requireContext())



        if (this.arguments != null) {
            bundle = this.requireArguments()
            bankID = bundle.getInt("bankID")
            quickProduct = bundle.getInt("quickProduct")
            totalPrice = bundle.getDouble("price")
        }


        Log.e("bank", bankID.toString())
        Log.e("product", quickProduct.toString())
        banksAdapter = BankSpinnerAdapter(this.requireContext())
        check_out_bank_select_spiner.adapter = banksAdapter
        check_out_deadline_select_spiner.adapter = instDurationAdapter


        if (quickProduct == 0) {
            viewModel.getCartItems().observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    installment.products = it
                }
            })
        } else {
            val item = CheckOutItem(quickProduct, 1)
            val newArr = listOf<CheckOutItem>(item)
            installment.products = newArr
        }

        viewModel.getBanks().observe(viewLifecycleOwner, Observer {
            it.let {
                banksAdapter.setData(it.banks)
                if (bankID != 0) {
                    installment.installment = bankID
                    check_out_bank_select_spiner.setSelection(bankID - 1)
                } else {
                    installment.installment = 1
                }
            }
        })
        viewModel.getUserInfo().observe(viewLifecycleOwner, Observer {
            it.let {
                installment.name = it.name.toString()
                installment.phone = it.phone.toString()
            }
        })

        check_out_bank_select_spiner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val install = banksAdapter.getData(position)
                instDurationAdapter.setData(install, totalPrice)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        check_out_deadline_select_spiner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val instId = instDurationAdapter.getData(position)
                installment.installment = instId.id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        checkout_installation.setOnClickListener {
            val phone = installation_phone.text.toString()
            val name = installation_name.text.toString()
            if (phone.isNotEmpty() && name.isNotEmpty()) {
                installment.name = name
                installment.phone = phone
                Log.e("LIST", installment.products.toTypedArray().contentToString())
                MaterialAlertDialogBuilder(requireContext(), R.style.DialogTheme)
                    .setTitle("Авранг")
                    .setMessage("Подтверждаете ли вы покупку товаров в расрочку")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        viewModel.take_loan(installment).observe(viewLifecycleOwner, {
                            Log.e("IS", it.toString())
                            if (it) {
                                MaterialAlertDialogBuilder(requireContext(), R.style.DialogTheme)
                                    .setTitle("Авранг")
                                    .setMessage("Покупка в рассрочку произведена успешно")
                                    .setPositiveButton("OK") { _, _ ->
                                        val navBuilder = NavOptions.Builder()
                                        val navOptions =
                                            navBuilder.setPopUpTo(R.id.navigation_home, false).build()
                                        if (quickProduct == 0) {
                                            viewModel.cartRepository.remoeFromAll()
                                            requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
                                                .removeBadge(R.id.navigation_cart)
                                        }
                                        findNavController().navigate(R.id.navigation_cart,null,navOptions)

                                    }.show()
//                                if (quickProduct == 0) {
//                                    findNavController().navigate(R.id.navigation_cart)
//                                    viewModel.cartRepository.remoeFromAll()
//                                    requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
//                                        .removeBadge(R.id.navigation_cart)
//                                } else {
//                                    findNavController().navigate(R.id.navigation_cart)
//                                }
                            }


                        })
                    }
                    .setNegativeButton(
                        "Отмена"
                    ) { _, _ -> }
                    .show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Заполните соответсвующие поля",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }


}