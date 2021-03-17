package tj.colibri.avrang.data.categories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubCategory (
    val id: Int,
    val title : String
) : Parcelable