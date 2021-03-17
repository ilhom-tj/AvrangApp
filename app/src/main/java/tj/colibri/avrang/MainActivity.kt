package tj.colibri.avrang

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import tj.colibri.avrang.network.repositories.registrationRepo.RegistrationRepo
import tj.colibri.avrang.network.repositories.userRepo.UserRepository
import tj.colibri.avrang.utils.RefresToken
import tj.colibri.avrang.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var actionBarView : View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        initializeCustomActionBar()
        navController = findNavController(R.id.nav_host_fragment)
        var signInInstance = R.id.loginFragment
        val sessionManager  = SessionManager(this)
        if (sessionManager.getToken() != "error"){
                signInInstance = R.id.navigation_profile
                RefresToken()

        }

        navView.setOnNavigationItemReselectedListener {

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
            val logo : ImageView = actionBarView.findViewById(R.id.logo)
            val title : TextView = actionBarView.findViewById(R.id.action_bar_title)
            val icon : ImageView = actionBarView.findViewById(R.id.category_icon)
            when(destination.id) {
                R.id.navigation_home -> {
                    supportActionBar?.elevation = 0f
                    logo.isVisible = true
                    title.isVisible = false
                    icon.isVisible = false
                }
                R.id.productInfoFragment -> {
                    supportActionBar?.elevation = 0f
                    logo.isVisible = false
                    title.isVisible = false
                    icon.isVisible = false
                }
                R.id.categoriesFragment -> {
                    supportActionBar?.elevation = 10f
                    logo.isVisible = false
                    title.isVisible = true
                    icon.isVisible = true
                }
                R.id.productsInCategoriesFragment -> {
                    supportActionBar?.elevation = 0f
                    logo.isVisible = false
                    title.isVisible = true
                    icon.isVisible = false
                }
                else ->  {
                    supportActionBar?.elevation = 0f
                    logo.isVisible = false
                    title.isVisible = true
                    title.text = navController.currentDestination?.label
                    icon.isVisible = false
                    navView.isVisible = true
                }

            }
        }
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

    fun RefresToken(){
        val oldToken = SessionManager(this).getToken()
        val phone = SessionManager(this).getPhone()
        val password = SessionManager(this).getPassword()
        RegistrationRepo(this).login(phone!!, password!!).observe(this, Observer {
            it.let {
                Log.e("oldToken" , oldToken.toString())
                Log.e("newToken",it.access_token)
                if (oldToken != it.access_token){
                    SessionManager(this).setToken(it.access_token)
                }
            }
        })
    }
}