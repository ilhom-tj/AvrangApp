package tj.colibri.avrang.models.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attribute_values (
	@SerializedName("id") val id : Int,
	@SerializedName("attribute_id") val attribute_id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("attributes") val attributes : String
) : Parcelable