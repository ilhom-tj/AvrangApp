package tj.colibri.avrang.models.Category

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("categories") val categories : List<Children>
)