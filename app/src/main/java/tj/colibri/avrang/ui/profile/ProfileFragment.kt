package tj.colibri.avrang.ui.profile

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_fragment.*
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.ProductCardAdapter
import tj.colibri.avrang.models.Product.ProductCard2
import tj.colibri.avrang.data.User.User
import tj.colibri.avrang.databinding.ProfileFragmentBinding
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.SessionManager

class ProfileFragment : Fragment(), ProductCardAdapter.ItemClicked {

    private lateinit var viewModel: ProfileViewModel

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var user : User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.getRoot()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)



        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.profile.observe(viewLifecycleOwner,  {
            it.let {
                user = it
                Glide.with(requireActivity()).load(Const.image_url + it.image).into(binding.userAvatar)
                user_name.text = it.name
            }
        })

        Log.e("token", SessionManager(requireContext()).getToken().toString())
        if (SessionManager(requireContext()).getToken() == "error") {
            findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)
        }


        setHasOptionsMenu(true)
        personal_info_card.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_navigation_profile_to_profile_myinfo)
        }


        delivery_address_card.setOnClickListener {
            val action = ProfileFragmentDirections.actionNavigationProfileToDeliveryAdres(user)
            findNavController().navigate(action)
        }

        my_orders_card.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_navigation_profile_to_myOrders)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.option, menu)
    }

    @SuppressLint("InflateParams")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.more) {
            bottomsheet.showWithSheetView(
                LayoutInflater.from(requireContext()).inflate(R.layout.profile_more_option, null)
            )
            //EXIT
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_exit).setOnClickListener {
                SessionManager(requireContext()).deleteAll()
                findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)
            }
            //CONTACTS
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_contacts)
                .setOnClickListener {
                    findNavController().navigate(R.id.action_navigation_profile_to_contactsFragment)
                }
            //FAQ
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_faq).setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_FAQFragment)

            }
            //ABOUT
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_about).setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_aboutFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onProductItemClicked(product: ProductCard2) {

        findNavController().navigate(R.id.action_navigation_home_to_productInfoFragment)
    }

    override fun onAddProductToFavorite(favorite: ProductCard2) {
        viewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: ProductCard2) {
        viewModel.deleteFavorite(favorite)
    }

}