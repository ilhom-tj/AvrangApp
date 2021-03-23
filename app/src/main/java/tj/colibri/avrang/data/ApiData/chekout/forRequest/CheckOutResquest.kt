package tj.colibri.avrang.data.ApiData.chekout.forRequest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tj.colibri.avrang.data.ApiData.chekout.CheckOutItem

@Parcelize
data class CheckOutResquest(
    @SerializedName("products") val products: Array<CheckOutItem>,
    @SerializedName("total") var total: Double,
    @SerializedName("comments") val comments: String,
    @SerializedName("receiving_type") val receiving_type: Int,
    @SerializedName("payment_type") val payment_type: Int,
    @SerializedName("city") val city: String,
    @SerializedName("address") val address: String,
    @SerializedName("right_time") val right_time: String,
    @SerializedName("landmark") val landmark: String,
    @SerializedName("bonuses") val bonuses: Int
) : Parcelable