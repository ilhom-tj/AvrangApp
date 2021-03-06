package tj.colibri.avrang.data.Order

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderItem(
    val id : Int,
    val quantity : Int
) : Parcelable