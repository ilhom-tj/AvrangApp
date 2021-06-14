package tj.colibri.avrang.ui.checkout.chekoutprepare

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.check_out_prepare_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.WebActivity
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResquest


class CheckOutPrepareFragment : Fragment() {

    private val args : CheckOutPrepareFragmentArgs by navArgs()
    private lateinit var checkOut : CheckOutResquest

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
        checkOut = args.chekOutRequest
        Log.e("Respo", args.chekOutRequest.toString())
        cart_check_out.setOnClickListener {
            if (checkOut.payment_type == 1){
                viewModel.checkOut(checkOut).observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        viewModel.clearCart()
                        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).removeBadge(R.id.navigation_cart)
                        findNavController().navigate(R.id.navigation_cart)
                    } else {

                    }
                })
            }
            if (checkOut.payment_type == 2){
                viewModel.checkOutBank(checkOut).observe(viewLifecycleOwner, Observer {
                    if (it.success) {
                        Log.e("link",it.redirect)
                        val httpIntent = Intent(requireActivity(),WebActivity::class.java).apply {
                            putExtra("redirect",it.redirect)
                            viewModel.clearCart()
                        }
                        //httpIntent.data = Uri.parse(it.redirect)
                        startActivity(httpIntent)
                    }
                })
            }

        }
        SetupUI()
    }

    @SuppressLint("SetTextI18n")
    fun SetupUI(){
        check_out_prepare_goods_sum.text = "Итого товаров на сумму: ${args.chekOutRequest.total} TJS"
        check_out_prepare_discount_from_bonus.text = "Скидка по бонусам: 0 TJS"
        if (args.chekOutRequest.payment_type == 1){
            check_out_prepare_pay_method.text = "Способ оплаты: Наличные"
        }else if (args.chekOutRequest.payment_type == 2){
            check_out_prepare_pay_method.text = "Способ оплаты: Корти Милли"
        }
        if (args.chekOutRequest.total < 1000.0){
            if (args.chekOutRequest.delivery == 1){
                check_out_prepare_delivery_sum.text = "Cтоимость доставки : 20 TJS"
                checkOut.total += 20
            }else if (args.chekOutRequest.delivery ==2 ){
                check_out_prepare_delivery_sum.text = "Cтоимость доставки : 30 TJS"
                checkOut.total += 30
            }
        }else{
            check_out_prepare_delivery_sum.text = "Cтоимость доставки : 0 TJS"
        }
        check_out_prepare_bonus_for_purchase.text = "Бонусы с покупки: +0 баллов"
        check_out_prepare_total_sum.text = "Всего к оплате: ${checkOut.total} TJS"
    }
}