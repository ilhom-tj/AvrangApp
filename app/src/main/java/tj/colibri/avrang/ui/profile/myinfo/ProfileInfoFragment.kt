package tj.colibri.avrang.ui.profile.myinfo

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile_myinfo.*
import tj.colibri.avrang.R
import tj.colibri.avrang.databinding.FragmentProfileMyinfoBinding
import tj.colibri.avrang.utils.BitmapUtily
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ProfileInfoFragment : Fragment() {

    var myCalendar: Calendar? = Calendar.getInstance()
    private lateinit var viewModel: ProfileInfoViewModel
    private lateinit var profileMyinfoBinding: FragmentProfileMyinfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileMyinfoBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile_myinfo,
            container,
            false
        )
        profileMyinfoBinding.lifecycleOwner = this
        return profileMyinfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileInfoViewModel::class.java)
        profileMyinfoBinding.viewmodel = viewModel

        profileMyinfoBinding.avatar.setOnClickListener {
            pickImage()
        }

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

        val cityAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            R.id.text, cityArr
        )

        info_cities_spinner.adapter = cityAdapter

        viewModel.getCities().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                cityArr.clear()
                for (city in it) {
                    Log.e("City", city.name)
                    cityArr.add(city.name)
                }
                cityAdapter.notifyDataSetChanged()
            }
        })


        var add_adress = ""

        viewModel.user.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                content.visibility = View.VISIBLE
                progress.visibility = View.GONE
                Glide.with(requireActivity()).load(Const.image_url + it.image).into(
                    profileMyinfoBinding.avatar
                )
                if (it.gender == 0) {
                    info_sex_spinner.setSelection(1)
                }
                if (it.gender == 1) {
                    info_sex_spinner.setSelection(0)
                }
                if (it.city_id == 1) {
                    info_cities_spinner.setSelection(0)
                }
                if (it.city_id == 2) {
                    info_cities_spinner.setSelection(1)
                }
                add_adress = it.additional_address.toString()
            }
        })

        val Bdate =
            OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar?.set(Calendar.YEAR, year)
                myCalendar?.set(Calendar.MONTH, monthOfYear)
                myCalendar?.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

        edit_birthday.setOnClickListener {
            myCalendar?.get(Calendar.DAY_OF_MONTH)?.let { it1 ->
                DatePickerDialog(
                    requireContext(),
                    R.style.MyTimePickerDialogTheme,
                    Bdate,
                    myCalendar!![Calendar.YEAR],
                    myCalendar?.get(Calendar.MONTH)!!,
                    it1
                ).show()
            }
        }
        save_changes.setOnClickListener {
            val name = edit_name.text.toString()
            val phone = edit_phone.text.toString()
            val additional_phone = edit_extra_phone.text.toString()

            val gender: Int
            var city_id: Int? = 1
            if (pols[info_sex_spinner.selectedItemPosition] == "Мужской") {
                gender = 1
            } else {
                gender = 0
            }
            if (cityArr[info_cities_spinner.selectedItemPosition] == "Душанбе") {
                city_id = 1
            } else {
                city_id = 2
            }

            val birthdate = edit_birthday.text.toString()
            val email = edit_email.text.toString()

            val adress = edit_adres.text.toString()
            Log.e("additional1", additional_phone)
            viewModel.updateUserPersonalInfo(
                name,
                birthdate,
                phone,
                email,
                additional_phone,
                city_id,
                gender,
                adress,
                add_adress
            ).observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(requireContext(), "Данные сохранены", Toast.LENGTH_SHORT).show()
                }
            }
            Features().hideKeyboard(requireActivity())
        }
    }


    private fun updateLabel() {
        val myFormat = "dd-MM-yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        edit_birthday.setText(sdf.format(myCalendar!!.time))
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {
            try {
                data?.let {
                    val inputStream: InputStream? =
                        context?.contentResolver?.openInputStream(it.data!!)
                    val bufferedInputStream = BufferedInputStream(inputStream)
                    val bmp = BitmapFactory.decodeStream(bufferedInputStream)

                    val bytes = ByteArrayOutputStream()
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bytes)

                    val compressed =
                        BitmapFactory.decodeStream(ByteArrayInputStream(bytes.toByteArray()))


                    if (BitmapUtily.isStoragePermissionGranted(requireActivity())) {
                        val path = BitmapUtily.getImagePath(
                            it.data, requireActivity()
                        )
                        val rotated = BitmapUtily.modifyOrientation(compressed, path)
                        profileMyinfoBinding.avatar.setImageBitmap(rotated)

                        val path1 = MediaStore.Images.Media.insertImage(
                            requireContext().contentResolver,
                            rotated,
                            "photo",
                            ""
                        )
                        val stream =
                            context?.contentResolver?.openInputStream(Uri.parse(path1))
                        profileMyinfoBinding.avatarProgress.visibility = View.VISIBLE

                        stream?.let { stream1 ->
                            viewModel.updateProfileImage(stream1).observe(viewLifecycleOwner) {
                                it.let {
                                    if (it) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Загрузка фотографии профиля прошла успешно",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        profileMyinfoBinding.avatarProgress.visibility = View.GONE
                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "При загрузке фотографии произошла ошибка",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        profileMyinfoBinding.avatarProgress.visibility = View.GONE
                                    }
                                }
                            }
                        }

                    }
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val PICK_PHOTO = 1
    }


}