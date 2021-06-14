package tj.colibri.avrang.data.ApiData.FAQ

import com.google.gson.annotations.SerializedName

data class FAQRequest (
    @SerializedName("faqs") val faqs : List<FAQs>
    )