package tj.colibri.avrang.models.Installment

import com.google.gson.annotations.SerializedName



data class Installment (
	@SerializedName("id") val id : Int,
	@SerializedName("bank_id") val bank_id : Int,
	@SerializedName("months") val months : Int,
	@SerializedName("percent") val percent : Int,
	@SerializedName("prepayment") val prepayment : Int,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String
)