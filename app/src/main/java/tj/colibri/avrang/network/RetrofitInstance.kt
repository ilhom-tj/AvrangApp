package tj.colibri.avrang.network

import android.app.Application
import android.content.Context
import android.util.Log
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.SessionManager
import java.net.CookieHandler
import java.net.CookieManager

class RetrofitInstance(context: Context) {
    var cookieHandler: CookieHandler = CookieManager()

    val client = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(cookieHandler))
        .addInterceptor { chain ->
            val original = chain.request()

            var requestBuilderForToken = original.newBuilder()
                    .addHeader("Authorization","Bearer "+SessionManager(context).getToken())
                    .addHeader("Accept", "application/json")
                    .method(original.method(), original.body())
            val requestToken = requestBuilderForToken.build()


            var requestBuilder = original.newBuilder()
               .addHeader("Accept", "application/json")
                .method(original.method(), original.body())

            val request = requestBuilder.build()

            if (SessionManager(context).getToken() != "error"){
                Log.e("Session", SessionManager(context).getToken().toString())
                chain.proceed(requestToken)
            }else{
                chain.proceed(request)
            }
        }.build()

    private fun retrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun api() : Api {
        return retrofitInstance().create(Api::class.java)
    }
}