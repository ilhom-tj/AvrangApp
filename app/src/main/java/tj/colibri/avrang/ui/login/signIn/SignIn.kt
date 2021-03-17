package tj.colibri.avrang.ui.login.signIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.sign_in_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.utils.SessionManager

class SignIn : Fragment() {

    companion object {
        fun newInstance() = SignIn()
    }

    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        signIn_signup.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_phoneInputFragment)
        }
        signIn_enter.setOnClickListener {
            val phone = signIn_phonenum.text.toString()
            val password = signIn_password.text.toString()
            viewModel.login(phone,password).observe(viewLifecycleOwner, Observer {
                it.let {
                    viewModel.setUser(it.user)
                    SessionManager(requireContext()).setToken(it.access_token)
                    findNavController().navigate(R.id.action_signIn_to_navigation_profile)
                }
            })
        }

    }

}