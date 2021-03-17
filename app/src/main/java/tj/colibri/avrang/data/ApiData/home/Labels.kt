package tj.colibri.avrang.data.ApiData.home

import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName



//class LabelConverter{
//	@TypeConverter
//	fun converLabel(labels: Labels) : String{
//		val str = labels.has_gift.toString()
//	}
//}
data class Labels (

	@SerializedName("is_hit") val is_hit : Boolean,
	@SerializedName("is_promotion") val is_promotion : Boolean,
	@SerializedName("has_gift") val has_gift : Boolean
)