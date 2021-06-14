package tj.colibri.avrang.models.Installment

import com.google.gson.annotations.SerializedName



data class Banks (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("image") val image : String,
	@SerializedName("installments") val installments : List<Installment>
)