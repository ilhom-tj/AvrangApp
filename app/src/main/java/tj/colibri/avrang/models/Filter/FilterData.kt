package tj.colibri.avrang.models.Filter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterData(
    var q : String? = null,
    var priceFrom : Float? = null,
    var priceTo : Float? = null,
    var currentCategories : String? = null,
    var pages : Int? = 0,
    var attributes : String? = null,
    var sort : String? = null,
    var filter : String? = null,
    var brand : String? = null
) : Parcelable