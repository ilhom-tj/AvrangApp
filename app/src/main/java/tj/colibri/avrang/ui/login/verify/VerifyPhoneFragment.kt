package tj.colibri.avrang.ui.login.verify

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.login_verify_phone_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.ui.login.password.PasswordFragmentArgs

class VerifyPhoneFragment : Fragment() {

    companion object {
        fun newInstance() = VerifyPhoneFragment()
    }


    val args : VerifyPhoneFragmentArgs by navArgs()

    private lateinit var viewModel: VerifyPhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_verify_phone_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VerifyPhoneViewModel::class.java)
        // TODO: Use the ViewModel
      //  confirmCodePin.setText(args.confirmCode.toString())
//        var counter = object : CountDownTimer(60000,1000){
//            @SuppressLint("SetTextI18n")
//            override fun onTick(millisUntilFinished: Long) {
//                resent_confirm_code.text = "Отправить код ещё раз через ${millisUntilFinished/1000} сек."
//            }
//
//            override fun onFinish() {
//
//            }
//
//        }
        verify_accept.setOnClickListener{
            viewModel.getVerificationSubmit(args.phone,args.confirmCode).observe(viewLifecycleOwner,
                Observer {
                    it.let {
                        if (it.confirmed){
                            val params = VerifyPhoneFragmentDirections.actionVerifyPhoneFragmentToPasswordFragment(args.phone,confirmCodePin.text.toString().toInt())
                            findNavController().navigate(params)
                        }
                    }
                })

        }

    }

}