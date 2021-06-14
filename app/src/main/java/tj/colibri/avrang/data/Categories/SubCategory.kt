package tj.colibri.avrang.data.Categories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubCategory (
    val id: Int,
    val title : String
) : Parcelable