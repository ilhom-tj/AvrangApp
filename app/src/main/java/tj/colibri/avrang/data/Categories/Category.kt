package tj.colibri.avrang.data.Categories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val title : String,
    val subcategories : List<SubCategory>
) : Parcelable