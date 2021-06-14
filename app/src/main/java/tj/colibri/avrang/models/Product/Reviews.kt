package tj.colibri.avrang.models.Product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tj.colibri.avrang.models.Product.ProductInfo.Body


@Parcelize
data class Reviews (
	@SerializedName("id") val id : Int,
	@SerializedName("parent_id") val parent_id : Int,
	@SerializedName("name") val userName: String,
	@SerializedName("rating") val rating : Int,
	@SerializedName("user_image") val userImage : String,
	@SerializedName("body") val body : List<Body>,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
) : Parcelable