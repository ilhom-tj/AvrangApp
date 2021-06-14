package tj.colibri.avrang.data.ApiData.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Attributes (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("attribute_values") val attribute_values : List<Attribute_values>
) : Parcelable