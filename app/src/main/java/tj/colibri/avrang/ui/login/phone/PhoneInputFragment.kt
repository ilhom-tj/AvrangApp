package tj.colibri.avrang.ui.login.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.login_phone_input_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.utils.Features
import kotlinx.android.synthetic.main.login_phone_input_fragment.login_phone_inputText as login_phone_inputText1

class PhoneInputFragment : Fragment() {

    companion object {
        fun newInstance() = PhoneInputFragment()
    }

    private lateinit var viewModel: PhoneInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_phone_input_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhoneInputViewModel::class.java)
        login_phone_continue.setOnClickListener {
            if (login_phone_inputText1.text.toString().isNotEmpty()){
                viewModel.getVerificationCode(login_phone_inputText1.text.toString()).observe(viewLifecycleOwner, {
                    val destination = PhoneInputFragmentDirections.actionPhoneInputFragmentToVerifyPhoneFragment(phone = login_phone_inputText1.text.toString(), it.confirm_code)
                    Features().hideKeyboard(requireActivity())
                    findNavController().navigate(destination)
                })

            }
        }
    }

}