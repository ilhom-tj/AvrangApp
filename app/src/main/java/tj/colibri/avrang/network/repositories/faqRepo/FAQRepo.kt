package tj.colibri.avrang.network.repositories.faqRepo

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.colibri.avrang.DataBase.DB.DataBase
import tj.colibri.avrang.models.FAQ.FAQRequest
import tj.colibri.avrang.models.FAQ.FAQs
import tj.colibri.avrang.network.RetrofitInstance

class FAQRepo(context: Context) {

    private val api = RetrofitInstance(context).api()
    private val faqDao = DataBase.getInstance(context).faqDao
    val faqs = faqDao.getAllFAQs()

    fun getAllFAQs(){
        api.getAllFAQs().enqueue(object : Callback<FAQRequest>{
            override fun onResponse(call: Call<FAQRequest>, response: Response<FAQRequest>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        addToLocal(it.faqs)
                    }
                }
            }

            override fun onFailure(call: Call<FAQRequest>, t: Throwable) {

            }

        })
    }

    private fun addToLocal(faqs : List<FAQs>) = GlobalScope.launch{
        faqs.forEach {
            faqDao.addFaq(it)
        }
    }
}