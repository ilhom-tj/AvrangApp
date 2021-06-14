package tj.colibri.avrang.models.Installment

import com.google.gson.annotations.SerializedName



data class InstallmentBanks(

	@SerializedName("banks") val banks : List<Banks>
)