package tj.colibri.avrang.models.Contact

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class City (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
) : Parcelable