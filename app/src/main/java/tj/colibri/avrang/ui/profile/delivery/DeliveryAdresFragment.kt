package tj.colibri.avrang.ui.profile.delivery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_delivery_adres.*
import tj.colibri.avrang.R

class DeliveryAdresFragment : Fragment() {

    private lateinit var viewModel: DeliveryAdresViewModel
    private val args: DeliveryAdresFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_delivery_adres, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeliveryAdresViewModel::class.java)

        edit_adres1.setText(args.user.main_address.toString())
        edit_adres2.setText(args.user.additional_address.toString())

        save_changes.setOnClickListener {
            val adres1: String = edit_adres1.text.toString()
            val adres2: String = edit_adres2.text.toString()
            viewModel.updateAdress(adres1, adres2).observe(viewLifecycleOwner) {
                it.let {
                    if (it){
                        Toast.makeText(requireContext(), "Данные сохранены", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
}