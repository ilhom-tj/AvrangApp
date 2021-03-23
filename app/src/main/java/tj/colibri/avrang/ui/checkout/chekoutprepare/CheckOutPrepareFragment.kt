package tj.colibri.avrang.ui.checkout.chekoutprepare

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.check_out_prepare_fragment.*
import tj.colibri.avrang.R

class CheckOutPrepareFragment : Fragment() {

    private val args : CheckOutPrepareFragmentArgs by navArgs()


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

        cart_check_out.setOnClickListener {
            viewModel.checkOut(args.chekOutRequest).observe(viewLifecycleOwner, Observer {
                if (it != null){
                    Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    viewModel.clearCart()
                }else{

                }
            })
        }
        SetupUI()
    }

    @SuppressLint("SetTextI18n")
    fun SetupUI(){
        check_out_prepare_goods_sum.setText("Итого товаров на сумму: ${args.chekOutRequest.total} TJS")
        check_out_prepare_discount_from_bonus.setText("Скидка по бонусам: 0 TJS")
        check_out_prepare_pay_method.setText("Способ оплаты: Наличные")
        check_out_prepare_bonus_for_purchase.setText("Бонусы с покупки: +0 баллов")
        check_out_prepare_total_sum.setText("Всего к оплате: ${args.chekOutRequest.total + 20} TJS")
        args.chekOutRequest.total = args.chekOutRequest.total + 20
    }
}