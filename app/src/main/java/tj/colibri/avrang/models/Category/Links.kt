package tj.colibri.avrang.models.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links (
	@SerializedName("total") val total : Int,
	@SerializedName("count") val count : Int,
	@SerializedName("per_page") val per_page : Int,
	@SerializedName("current_page") val current_page : Int,
	@SerializedName("total_pages") val total_pages : Int
) : Parcelable