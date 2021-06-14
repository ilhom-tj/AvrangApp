package tj.colibri.avrang.models.Product.ProductInfo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Body (
	@SerializedName("adventages")
	val advantages : String,

	@SerializedName("disadvantages")
	val disadvantages : String,

	@SerializedName("comments")
	val comment : String
) : Parcelable