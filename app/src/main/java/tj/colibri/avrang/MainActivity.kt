package tj.colibri.avrang

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tj.colibri.avrang.network.repositories.cartRepo.CartRepository
import tj.colibri.avrang.network.repositories.favoriteRepo.FavoriteRepository
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo
import tj.colibri.avrang.utils.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var actionBarView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        initializeCustomActionBar()
        navController = findNavController(R.id.nav_host_fragment)
        var signInInstance = R.id.loginFragment

        val sessionManager = SessionManager(this)
        if (sessionManager.getToken() != "error") {
            signInInstance = R.id.navigation_profile
            RefresToken()
        }


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_catalog,
                R.id.navigation_favorite,
                signInInstance,
                R.id.navigation_cart
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            actionBar?.title = navController.currentDestination?.label
            val logo: ImageView = actionBarView.findViewById(R.id.logo)
            val title: TextView = actionBarView.findViewById(R.id.action_bar_title)
            val icon: ImageView = actionBarView.findViewById(R.id.category_icon)
            when (destination.id) {
                R.id.navigation_home -> {
                    supportActionBar?.elevation = 0f
                    supportActionBar?.show()
                    logo.visibility = View.VISIBLE
                    title.visibility = View.GONE
                    icon.visibility = View.GONE
                }
                R.id.productInfoFragment -> {
                    supportActionBar?.elevation = 0f
                    supportActionBar?.show()
                    logo.visibility = View.GONE
                    title.isVisible = false
                    icon.visibility = View.GONE
                }
                R.id.categoriesFragment -> {
                    supportActionBar?.elevation = 10f
                    supportActionBar?.show()
                    logo.visibility = View.GONE
                    title.isVisible = true
                    icon.visibility = View.GONE
                }
                R.id.productsInCategoriesFragment -> {
                    supportActionBar?.elevation = 0f

                    supportActionBar?.show()
                    logo.isVisible = false
                    title.isVisible = true
                    icon.isVisible = false
                }
                R.id.navigation_cart ->{
                    supportActionBar?.elevation = 0f

                    logo.isVisible = false
                    title.isVisible = true
                    title.text = navController.currentDestination?.label
                    icon.isVisible = false
                    navView.isVisible = true
                }
                else -> {
                    supportActionBar?.elevation = 0f
                    supportActionBar?.show()
                    logo.isVisible = false
                    title.isVisible = true
                    title.text = navController.currentDestination?.label
                    icon.isVisible = false
                    navView.isVisible = true
                }

            }
        }
        val cartRepository = CartRepository(this)

        val favRepository = FavoriteRepository(this.application)
        favRepository.getFavorite().observe(this,  {
            it.let {
                if (it.favorites.isNotEmpty()){
                    it.favorites.forEach { fav ->
                        favRepository.addItemToChache(Features().toFavoriteCache(fav))
                    }
                }
            }
        })
        cartRepository.checkOutList.observe(this,  { items ->
            cartRepository.cartIndexes.observe(this,  {
                it.let {
                    if(it.isEmpty()){
                        navView.removeBadge(R.id.navigation_cart)
                    }else{
                        navView.getOrCreateBadge(R.id.navigation_cart).number = it.size

                    }
                    cartRepository.updateCart(it,items)
                }
            })
        })

        NetworkStatus(this).startNetworkCallback()
        NetworkStatus.NetworkStatus.isConnected.observe(this,  {
            if(it ){
                noInternet.visibility = View.GONE
                container.visibility = View.VISIBLE
            }else{
                noInternet.visibility = View.VISIBLE
                container.visibility = View.GONE
            }
        })


    }

    private fun initializeCustomActionBar() {
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.avrang_action_bar)
        actionBarView = supportActionBar!!.customView
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    private fun RefresToken() {
        val oldToken = SessionManager(this).getToken()
        val phone = SessionManager(this).getPhone()
        val password = SessionManager(this).getPassword()
        RegistrationRepo(this).login(phone!!, password!!).observe(this,  {
            it.let {
                Log.e("oldToken", oldToken.toString())
                Log.e("newToken", it.access_token)
                if (oldToken != it.access_token) {
                    SessionManager(this).setToken(it.access_token)
                }
            }
        })
    }
}