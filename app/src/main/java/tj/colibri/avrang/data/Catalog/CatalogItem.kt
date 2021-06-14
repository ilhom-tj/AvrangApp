package tj.colibri.avrang.data.Catalog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatalogItem (
    val id : Int,
    val title : String,
    val icon : String
) : Parcelable