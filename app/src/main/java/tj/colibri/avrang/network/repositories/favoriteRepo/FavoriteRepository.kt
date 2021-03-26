package tj.colibri.avrang.network.repositories.favoriteRepo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.data.ApiData.Favorite.FavoriteRequest
import tj.colibri.avrang.data.favorite.FavoriteCard
import tj.colibri.avrang.data.favorite.FavoriteDB
import tj.colibri.avrang.data.mock.ProductCard2
import tj.colibri.avrang.network.RetrofitInstance
import tj.colibri.avrang.utils.Features

class FavoriteRepository(application: Application) {

    private val api = RetrofitInstance(application.applicationContext).api()

    private val favoriteDao = FavoriteDB.getInstance(application.applicationContext).favorite
    //Favorite Live Data
    var favList = favoriteDao.getAllFavorites()
    var favCount = favoriteDao.getFavCount()

    fun getFavorite() : LiveData<FavoriteRequest>{
        var liveData = MutableLiveData<FavoriteRequest>()
        api.getFavorites().enqueue(object : Callback<FavoriteRequest>{
            override fun onResponse(
                call: Call<FavoriteRequest>,
                response: Response<FavoriteRequest>
            ) {
                if (response.isSuccessful){
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<FavoriteRequest>, t: Throwable) {

            }

        })
        return liveData
    }
    fun addFavorite(id : Int) : LiveData<Boolean>{
        var isCreated = MutableLiveData<Boolean>()
        api.addToFavorite(id).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                isCreated.value = response.code() == 201
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })
        return isCreated
    }

    fun addItemToFavorite(favorite: FavoriteCard) : LiveData<Boolean>{
        var isCreated = MutableLiveData<Boolean>()
        addItemToChache(favorite)
        api.addToFavorite(favorite.id).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                isCreated.value = response.code() == 201
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })
        return isCreated
    }
    fun deleteFavorite(favorite: FavoriteCard) : LiveData<Boolean>{
        val isDeleted = MutableLiveData<Boolean>()
        deleteFromChache(favorite)
        api.deleteFavorite(favorite.id).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                isDeleted.value = response.code() == 204
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })
        return isDeleted
    }

    fun addItemToChache(favoriteCard: FavoriteCard) = GlobalScope.launch {
        favoriteDao.insert(favoriteCard)
    }
    fun deleteFromChache(favoriteCard: FavoriteCard) = GlobalScope.launch {
        favoriteDao.deleteFavorite(favoriteCard)
    }
}