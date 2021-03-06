package tj.colibri.avrang.data.User

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "table_user")
data class User (
	@PrimaryKey(autoGenerate = false)
	val id : String,

	@ColumnInfo(name = "user_name")
	var name : String?,

	@ColumnInfo(name = "user_birthdate")
	var birthdate : String?,

	@ColumnInfo(name = "user_gender")
	var gender : Int?,

	@ColumnInfo(name = "user_main_address")
	var main_address : String?,

	@ColumnInfo(name = "user_additional_address")
	var additional_address : String?,

	@ColumnInfo(name = "user_city_id")
	var city_id : Int?,

	@ColumnInfo(name = "user_phone")
	var phone : String?,

	@ColumnInfo(name = "user_additional_phone")

	@SerializedName("additional_phone")
	var additional_phone : String?,

	@ColumnInfo(name = "user_slug")
	var slug : String?,

	@ColumnInfo(name = "user_bonus_balance")
	var bonus_balance : String?,

	@ColumnInfo(name = "user_email")
	var email : String?,

	@ColumnInfo(name = "user_verified_at")
	var verified_at : String?,

	@ColumnInfo(name = "user_image")
	var image : String?,

	@ColumnInfo(name = "user_deleted_at")
	var deleted_at : String?,

	@ColumnInfo(name = "user_created_at")
	var created_at : String?,

	@ColumnInfo(name = "user_updated_at")
	var updated_at : String?,

//	@SerializedName("city") val city : City
) : Parcelable
