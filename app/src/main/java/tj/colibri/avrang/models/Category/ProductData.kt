package tj.colibri.avrang.models.Category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tj.colibri.avrang.models.Product.ProductCard2

@Parcelize
data class ProductData(
    @SerializedName("data") val products : List<ProductCard2>,
    @SerializedName("links") val links : Links
) : Parcelable