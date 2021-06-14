package tj.colibri.avrang.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import tj.colibri.avrang.utils.NetworkStatus.NetworkStatus.isConnected

class NetworkStatus(private val context: Context) {

    object NetworkStatus {
        var isConnected = MutableLiveData<Boolean>()
    }

    fun startNetworkCallback() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()

        isConnected.postValue(false)

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected.postValue(true)
                }
                override fun onLost(network: Network) {
                    isConnected.postValue(false)
                }
            }
        )
    }


}