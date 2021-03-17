package tj.colibri.avrang.ui.favorites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import tj.colibri.avrang.R
import tj.colibri.avrang.data.mock.MockData
import tj.colibri.avrang.adapters.FavoritesAdapter
import tj.colibri.avrang.data.favorite.Favorite

class FavoritesFragment : Fragment(), FavoritesAdapter.RemoveClickListener {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(this).get(FavoritesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        favoritesAdapter = FavoritesAdapter(this,this)

        favorites_recycler_view.adapter = favoritesAdapter
        favorites_recycler_view.layoutManager = GridLayoutManager(context, 1,  LinearLayoutManager.VERTICAL, false)
        viewModel.favList.observe(requireActivity(), Observer {
            it.let {
                val list = ArrayList(it)
                favoritesAdapter.setData(list)
                favorites_qty.text = favoritesAdapter.itemCount.toString() + " товара"
            }
        })




    }

    override fun removeClickListener(favorite: Favorite) {
        viewModel.deleteFavorite(favorite)
    }


}