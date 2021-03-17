package tj.colibri.avrang.ui.login.password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.login_password_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.data.mock.MockData
import tj.colibri.avrang.data.user.User
import tj.colibri.avrang.utils.SessionManager

class PasswordFragment : Fragment() {

    companion object {
        fun newInstance() = PasswordFragment()
    }

    private lateinit var viewModel: PasswordViewModel

    val args : PasswordFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PasswordViewModel::class.java)



        signUp_enter.setOnClickListener{
            Log.e("phone",args.phone)
            Log.e("code",args.confirmCode.toString())
            Log.e("pass1",signup_password.text.toString())
            Log.e("pass2",signup_confirm_password.text.toString())
            viewModel.registrationUser(args.phone,signup_password.text.toString(),signup_confirm_password.text.toString()).observe(
                viewLifecycleOwner,
                Observer {
                    it.let {
                        viewModel.setUser(it.user)
                        SessionManager(requireContext()).setToken(it.access_token)
                        findNavController().navigate(R.id.action_passwordFragment_to_navigation_profile)
                    }
                }
            )
//            val token = MockData.fakeToken
//            SessionManager(requireContext()).setToken(token)
//           // val user = MockData.user
//          //  viewModel.setUser(user)
//            findNavController().currentBackStackEntry
        }
    }

}