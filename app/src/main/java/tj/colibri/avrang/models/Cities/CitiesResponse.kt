package tj.colibri.avrang.models.Cities

import com.google.gson.annotations.SerializedName
import tj.colibri.avrang.data.Cities.Cities

data class CitiesResponse (
    @SerializedName("cities") val cities : List<Cities>
)