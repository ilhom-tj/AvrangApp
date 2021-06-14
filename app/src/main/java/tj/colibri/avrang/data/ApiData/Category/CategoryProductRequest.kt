package tj.colibri.avrang.data.ApiData.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tj.colibri.avrang.data.ApiData.product.ProductInfo.Banks

@Parcelize
data class CategoryProductRequest(
    @SerializedName("categories") val categories : List<Categories>,
    @SerializedName("products") val products : ProductData,
    @SerializedName("priceRangeFrom") val priceRangeFrom : Double,
    @SerializedName("priceRangeTo") val priceRangeTo : Double,
    @SerializedName("attributes") val attributes : List<Attributes>,
    @SerializedName("brands") val brands : List<Brands>,
    @SerializedName("banks") val banks : List<Banks>,
) : Parcelable