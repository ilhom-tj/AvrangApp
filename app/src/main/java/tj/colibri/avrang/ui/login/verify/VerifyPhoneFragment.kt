package tj.colibri.avrang.ui.login.verify

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.login_verify_phone_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.utils.Features

class VerifyPhoneFragment : Fragment() {

    companion object {
        fun newInstance() = VerifyPhoneFragment()
    }

    var confirmCode = 0

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

        confirmCode = args.confirmCode

        object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                if (resent_confirm_code != null){
                    resent_confirm_code.isClickable = false
                    resent_confirm_code.text = "Отправить код ещё раз через ${millisUntilFinished/1000} сек."
                }
            }
            override fun onFinish() {
                resent_confirm_code.isClickable = true
                resent_confirm_code.text = "Отправить код ещё раз"
            }
        }.start()

        resent_confirm_code.setOnClickListener {
            viewModel.getVerificationCode(args.phone).observe(viewLifecycleOwner, Observer {
                it.let {
                    confirmCode = it.confirm_code
                }
            })
        }

        verify_accept.setOnClickListener{
            viewModel.getVerificationSubmit(args.phone, confirmCode).observe(viewLifecycleOwner,
                Observer {
                    it.let {
                        if (it.confirmed) {
                            if (!confirmCodePin.text.toString().equals("")){
                                val params =
                                    VerifyPhoneFragmentDirections.actionVerifyPhoneFragmentToPasswordFragment(
                                        args.phone,
                                        confirmCodePin.text.toString().toInt()
                                    )
                                Features().hideKeyboard(requireActivity())
                                findNavController().navigate(params)
                            }
                        }
                    }
                })

        }

    }

}