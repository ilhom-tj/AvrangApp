package tj.colibri.avrang.data.ApiData.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tj.colibri.avrang.data.ApiData.product.ProductCard2

@Parcelize
data class ProductData(
    @SerializedName("data") val products : List<ProductCard2>,
    @SerializedName("links") val links : Links
) : Parcelable