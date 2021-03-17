package tj.colibri.avrang.data.ApiData.cities

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.cities.Cities

data class CitiesResponse (
    @SerializedName("cities") val cities : List<Cities>
)