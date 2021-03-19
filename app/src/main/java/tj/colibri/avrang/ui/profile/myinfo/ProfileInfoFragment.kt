package tj.colibri.avrang.ui.profile.myinfo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_profile_myinfo.*
import tj.colibri.avrang.R
import tj.colibri.avrang.databinding.FragmentProfileMyinfoBinding
import tj.colibri.avrang.utils.SessionManager
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ProfileInfoFragment : Fragment() {

    var myCalendar = Calendar.getInstance();
    private lateinit var viewModel : ProfileInfoViewModel
    private lateinit var profileMyinfoBinding: FragmentProfileMyinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      //  binding = ProfileFragmentBinding.inflate(inflater,container,false)
        profileMyinfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_myinfo,container,false)
        profileMyinfoBinding.lifecycleOwner = this
        return profileMyinfoBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileInfoViewModel::class.java)
        profileMyinfoBinding.viewmodel = viewModel

        SetupUI()
    }

    fun SetupUI(){


        var pols = ArrayList<String>()
        pols.add("Мужской")
        pols.add("Женский")


        var sexAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            R.id.text, pols
        )
        info_sex_spinner.adapter = sexAdapter



        val cityArr = ArrayList<String>()

        var cityAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            R.id.text,cityArr )

        info_cities_spinner.adapter = cityAdapter

        viewModel.getCities().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                for (city in it){
                    cityArr.add(city.name)
                }
                cityAdapter.notifyDataSetChanged()
            }
        })



        viewModel.user.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.e("USER",it.toString())
                if (it.gender == 0){
                    info_sex_spinner.setSelection(1)
                }
                if (it.gender == 1){
                    info_sex_spinner.setSelection(0)
                }
                if (it.city_id == 1){
                    info_cities_spinner.setSelection(1)
                }
                if (it.city_id == 2){
                    info_cities_spinner.setSelection(2)
                }
            }
        })

        val Bdate =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel() }

        edit_birthday.setOnClickListener{
            DatePickerDialog(
                requireContext(), R.style.MyTimePickerDialogTheme, Bdate, myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        save_changes.setOnClickListener {
            val name = edit_name.text.toString()
            val phone = edit_phone.text.toString()
            var additional_phone = edit_extra_phone.text.toString()

            val gender : Int
            var city_id : Int? = 1
            if (pols[info_sex_spinner.selectedItemPosition] == "Мужской"){ gender = 1 }else{ gender = 0 }
            if (cityArr[info_cities_spinner.selectedItemPosition] == "Душанбе"){city_id = 1 } else{ city_id = 2}

            val birthdate = edit_birthday.text.toString()
            val email = edit_email.text.toString()

            //Это 2ые кавычки
            val dm = 34.toChar()
            val adress = dm+edit_adres.text.toString()+dm

            Log.e("additional1",additional_phone)
            viewModel.updateUserPersonalInfo(
                name,
                birthdate,
                phone,
                email,
                additional_phone,
                city_id,
                gender,
                adress
            )
        }
    }


    private fun updateLabel() {
        val myFormat = "dd-MM-yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        edit_birthday.setText(sdf.format(myCalendar.getTime()))
    }
}