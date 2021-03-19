package tj.colibri.avrang.data.ApiData.home

import android.os.Parcelable
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//class LabelConverter{
//	@TypeConverter
//	fun converLabel(labels: Labels) : String{
//		val str = labels.has_gift.toString()
//	}
//}
@Parcelize
data class Labels (

	@SerializedName("is_hit") val is_hit : Boolean,
	@SerializedName("is_promotion") val is_promotion : Boolean,
	@SerializedName("has_gift") val has_gift : Boolean
) : Parcelable