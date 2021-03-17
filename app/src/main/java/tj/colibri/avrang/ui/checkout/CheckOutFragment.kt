package tj.colibri.avrang.ui.checkout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.check_out_bank_transaction.*
import kotlinx.android.synthetic.main.check_out_fragment.*
import kotlinx.android.synthetic.main.check_out_installation.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.BankSelectorAdapter
import tj.colibri.avrang.adapters.DeadlineSelectorAdapter
import tj.colibri.avrang.data.mock.MockData

class CheckOutFragment : Fragment() {


    private lateinit var viewModel: CheckOutViewModel
    private lateinit var bankSelectAdapter: BankSelectorAdapter
    private lateinit var dedlineSelectorAdapter: DeadlineSelectorAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.check_out_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CheckOutViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetUpUI()


        bankSelectAdapter = BankSelectorAdapter(
            requireActivity(),
            R.layout.bank_select_spinner_layout,
            R.id.txt,
            MockData.listofBanks
        )

        dedlineSelectorAdapter = DeadlineSelectorAdapter(
            requireActivity(),
            R.layout.dedline_select_spinner_layout,
            R.id.txt,
            MockData.listofDedlineSelect
        )
        check_out_bank_select_spiner.adapter = bankSelectAdapter
        check_out_deadline_select_spiner.adapter = dedlineSelectorAdapter


        checkout_card_push.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_checkOutFragment_to_checkOutReadyFragment)
        }
    }

    private fun SetUpUI() {
        self_method.setOnClickListener {
            onRadioButtonClicked(self_method)
        }
        delivery_method.setOnClickListener {
            onRadioButtonClicked(delivery_method)
        }

        payment_cash.setOnClickListener {
            onPayMethodRadioButtonClick(payment_cash)
        }
        payment_bank_transaction.setOnClickListener {
            onPayMethodRadioButtonClick(payment_bank_transaction)
        }
        payment_installment.setOnClickListener {
            onPayMethodRadioButtonClick(payment_installment)
        }

        dont_use_bonus_and_promo.setOnClickListener {
            onBonusAndPromoButtonClick(dont_use_bonus_and_promo)
        }
        use_bonus.setOnClickListener {
            onBonusAndPromoButtonClick(use_bonus)
        }
        use_promo.setOnClickListener {
            onBonusAndPromoButtonClick(use_promo) }

    }

    private fun onRadioButtonClicked(view: RadioButton) {
        val checked = view.isChecked

        // Check which radio button was clicked
        when (view.getId()) {
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

    private fun onPayMethodRadioButtonClick(view: RadioButton) {
        val checked = view.isChecked

        // Check which radio button was clicked
        when (view.getId()) {
            R.id.payment_cash ->
                if (checked) {
                    check_out_container_bank_transaction.visibility = View.GONE
                    check_out_container_installation.visibility = View.GONE
                }
            R.id.payment_bank_transaction ->
                if (checked) {
                    check_out_container_bank_transaction.visibility = View.VISIBLE
                    check_out_container_installation.visibility = View.GONE
                }
            R.id.payment_installment ->
                if (checked) {
                    check_out_container_bank_transaction.visibility = View.GONE
                    check_out_container_installation.visibility = View.VISIBLE

                }
        }
    }

    private fun onBonusAndPromoButtonClick(view: RadioButton) {
        val checked = view.isChecked
        // Check which radio button was clicked
        when (view.getId()) {
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

    private fun PushCheckOut(){

    }
}