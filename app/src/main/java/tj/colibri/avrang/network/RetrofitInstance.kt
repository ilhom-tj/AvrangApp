package tj.colibri.avrang.network

import android.content.Context
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
    private var cookieHandler: CookieHandler = CookieManager()

    private val client = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(cookieHandler))
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilderForToken = original.newBuilder()
                    .addHeader("Authorization","Bearer "+SessionManager(context).getToken())
                    .addHeader("Accept", "application/json")
                    .method(original.method(), original.body())
            val requestToken = requestBuilderForToken.build()


            val requestBuilder = original.newBuilder()
               .addHeader("Accept", "application/json")
                .method(original.method(), original.body())

            val request = requestBuilder.build()

            if (SessionManager(context).getToken() != "error"){
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