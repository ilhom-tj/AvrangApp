package tj.colibri.avrang.models.Installment

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.models.Chekout.CheckOutItem

data class PayClass(
    @SerializedName("products")
    var products : List<CheckOutItem>,
    @SerializedName("name")
    var name : String,
    @SerializedName("phone")
    var phone : String,
    @SerializedName("installment")
    var installment: Int
)