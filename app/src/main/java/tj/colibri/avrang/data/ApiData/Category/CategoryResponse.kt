package tj.colibri.avrang.data.ApiData.Category

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("categories") val categories : List<Categories>,
    @SerializedName("category") val category : List<Categories>,
    @SerializedName("subcategories") val subCategory : List<Categories>
)