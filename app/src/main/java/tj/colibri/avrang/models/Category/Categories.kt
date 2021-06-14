package tj.colibri.avrang.models.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Categories (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("icon") val icon : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("parent_id") val parent_id : Int,
	@SerializedName("children") val children : List<Children>
) : Parcelable