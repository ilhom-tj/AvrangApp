package tj.colibri.avrang.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.users_also_buy_recycler_view
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.ProductCardAdapter
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.databinding.ProfileFragmentBinding
import tj.colibri.avrang.utils.SessionManager

class ProfileFragment : Fragment(), ProductCardAdapter.ItemClicked {

    private lateinit var viewModel: ProfileViewModel
    private var productCardAdapter = ProductCardAdapter(this, this)

    private lateinit var binding : ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   binding = DataBindingUtil.setContentView(requireActivity(),R.layout.profile_fragment)
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_fragment,container,false)
        binding.lifecycleOwner = this
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       // val user = MockData.user


       // UserRepository(requireActivity().application).updateUser(user)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
      //  Log.e("USER",viewModel.user.value!!.name.toString())


        //CheckIf user signed
        Log.e("token" , SessionManager(requireContext()).getToken().toString())
        if (SessionManager(requireContext()).getToken() == "error"){
            findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)
        }


        setHasOptionsMenu(true)
        personal_info_card.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_profile_to_profile_myinfo)
        }


        delivery_address_card.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_profile_to_deliveryAdres)
        }

        my_orders_card.setOnClickListener{
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_profile_to_myOrders)
        }

        users_also_buy_recycler_view.adapter = productCardAdapter
        users_also_buy_recycler_view.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
     //   productCardAdapter.setData(MockData.listOfProducts)


        Glide.with(this).load("https://images.unsplash.com/photo-1566844116408-c5f235c19f61?ixid=MXwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDF8fHxlbnwwfHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=100").circleCrop().into(user_avatar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear();
        inflater.inflate(R.menu.option, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.more){
            bottomsheet.showWithSheetView(LayoutInflater.from(requireContext()).inflate(R.layout.profile_more_option,null))
            //EXIT
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_exit).setOnClickListener {
                SessionManager(requireContext()).deleteAll()
                findNavController().navigate(R.id.action_navigation_profile_to_loginFragment)
            }
            //CONTACTS
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_contacts).setOnClickListener {
                NavigateTo(R.id.action_navigation_profile_to_contactsFragment)
            }
            //FAQ
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_faq).setOnClickListener {
                NavigateTo(R.id.action_navigation_profile_to_FAQFragment)

            }
            //ABOUT
            bottomsheet.findViewById<ConstraintLayout>(R.id.profile_more_about).setOnClickListener {
                NavigateTo(R.id.action_navigation_profile_to_aboutFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun NavigateTo(id : Int){
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_profile_to_productInfoFragment)
      //  findNavController().navigate(id)
    }

    override fun onProductItemClicked(product: ProductCard2) {
        NavigateTo(R.id.action_navigation_home_to_productInfoFragment)
    }

    override fun onAddProductToFavorite(favorite: Favorite) {
        viewModel.addFavorite(favorite)
    }

    override fun onRemoveClickListener(favorite: Favorite) {
        viewModel.deleteFavorite(favorite)
    }

}