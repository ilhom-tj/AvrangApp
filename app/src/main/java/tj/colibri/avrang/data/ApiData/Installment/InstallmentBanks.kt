package tj.colibri.avrang.data.ApiData.Installment

import com.google.gson.annotations.SerializedName



data class InstallmentBanks(

	@SerializedName("banks") val banks : List<Banks>
)