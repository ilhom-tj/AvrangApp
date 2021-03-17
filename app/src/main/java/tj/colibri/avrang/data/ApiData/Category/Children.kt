package tj.colibri.avrang.data.ApiData.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Children (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("parent_id") val parent_id : Int,
	@SerializedName("icon") val icon : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("deleted_at") val deleted_at : String,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String,
	@SerializedName("parent") val parent : Parent
) : Parcelable