package tj.colibri.avrang.ui.login.resetPassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import tj.colibri.avrang.R
import tj.colibri.avrang.databinding.ResetPasswordFragmentBinding
import tj.colibri.avrang.utils.Features

class ResetPassword : Fragment() {


    private lateinit var binding : ResetPasswordFragmentBinding

    //STATE = 0 PHONE LAYOUT
    //STATE = 1 CONFIRM
    //STATE = 2 PASSWORD

    var CONTROL_STATE = 0

    companion object {
        fun newInstance() = ResetPassword()
    }

    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.reset_password_fragment,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    var conf_code = 0
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResetPasswordViewModel::class.java)
        binding.resetPasswordContinue.setOnClickListener {
            when(CONTROL_STATE){
                0 -> {
                    if (binding.resetPhoneInputText.text.isNotEmpty()){
                        viewModel.resetPassword(binding.resetPhoneInputText.text.toString())
                            .observe(viewLifecycleOwner, Observer {
                                if (it != null){
                                    Features().hideKeyboard(requireActivity())
                                    conf_code = it.confirm_code
                                    binding.resetState.text = "Подтвердить код из SMS"
                                    binding.resetPhoneLay.visibility = View.GONE
                                    binding.resetConfirmLay.visibility = View.VISIBLE
                                    CONTROL_STATE = 1
                                }
                            })
                    }
                }
                1 -> {
                    Log.e(binding.resetPhoneInputText.text.toString(),binding.confirmCodePin.text.toString())
                    if (binding.resetPhoneInputText.text.isNotEmpty() && binding.confirmCodePin.text!!.isNotEmpty()){
                        viewModel.resetPassword(binding.resetPhoneInputText.text.toString(),binding.confirmCodePin.text.toString())
                            .observe(viewLifecycleOwner, Observer {
                                if (it.confirmed){
                                    Features().hideKeyboard(requireActivity())
                                    binding.resetState.text = "Подтвердить пароль"
                                    binding.resetConfirmLay.visibility = View.GONE
                                    binding.resetPasswordLay.visibility = View.VISIBLE
                                    CONTROL_STATE = 2
                                }
                            })
                    }
                }
                2->{
                    val password = binding.resetPassword1.text.toString()
                    val passwordConfirm = binding.resetPassword2.text.toString()
                    val phone = binding.resetPhoneInputText.text.toString()
                    if (password.isNotEmpty() && passwordConfirm.isNotEmpty()){
                        if (passwordConfirm.equals(password)){
                            viewModel.changePassword(phone,password,passwordConfirm).observe(viewLifecycleOwner,
                                Observer {
                                    Log.e(password,passwordConfirm)
                                    if (it.message.isNotEmpty()){
                                        Toast.makeText(requireContext(),"Пароль изменен",Toast.LENGTH_SHORT).show()
                                        Features().hideKeyboard(requireActivity())
                                        Features().hideKeyboard(requireActivity())
                                        requireActivity().onBackPressed()
                                    }
                                })
                        }else{
                            Toast.makeText(requireContext(),"Пароли не совападют",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(requireContext(),"Поля не заполнены",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}