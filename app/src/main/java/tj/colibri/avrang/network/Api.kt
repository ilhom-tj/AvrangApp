package tj.colibri.avrang.network

import okhttp3.MultipartBody
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResquest
import retrofit2.Call
import retrofit2.http.*
import tj.colibri.avrang.models.Cart.CartIndexResponse
import tj.colibri.avrang.models.Cart.UpdateCart
import tj.colibri.avrang.models.Category.CategoryProductRequest
import tj.colibri.avrang.models.Category.CategoryResponse
import tj.colibri.avrang.models.Contact.ContactResponse
import tj.colibri.avrang.models.FAQ.FAQRequest
import tj.colibri.avrang.models.Installment.InstallmentBanks
import tj.colibri.avrang.models.Installment.PayClass
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutBankResponse
import tj.colibri.avrang.models.Chekout.forRequest.CheckOutResponse
import tj.colibri.avrang.models.Cities.CitiesResponse
import tj.colibri.avrang.models.Registration.ConfirmCode
import tj.colibri.avrang.models.Registration.RegistrationCallBack
import tj.colibri.avrang.models.Home.HomeResponse
import tj.colibri.avrang.models.Product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.models.Profile.Image.ImageResponse
import tj.colibri.avrang.models.Profile.ProfileResponse
import tj.colibri.avrang.data.OrderDetails
import tj.colibri.avrang.data.Order.MyOrdersRequest
import tj.colibri.avrang.data.User.User

interface Api {

    //Auth

    @POST("auth/login")
    fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Call<RegistrationCallBack>

    @POST("auth/number-check")
    fun firstStepRegister(
        @Query("phone") phone: String
    ): Call<ConfirmCode>

    @POST("auth/number-check")
    fun secondtStepRegister(
        @Query("phone") phone: String,
        @Query("confirm_code") confirm_code: String
    ): Call<ConfirmCode>


    //ResetPassword
    @POST("auth/number-check")
    fun resetPassword(
        @Query("phone") phone: String,
        @Query("reset") reset : Int = 1
    ) : Call<ConfirmCode>

    @POST("auth/number-check")
    fun resetPassword(
        @Query("phone") phone: String,
        @Query("confirm_code") confirm_code: String,
        @Query("reset") reset : Int  = 1
    ):Call<ConfirmCode>

    @POST("auth/change-password")
    fun changePassword(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
    ): Call<ConfirmCode>

    @POST("auth/register")
    fun registration(
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
        @Query("phone") phone: String
    ): Call<RegistrationCallBack>


    //tj.colibri.avrang.models.orderDetails.tj.colibri.avrang.data.user.User settings
    @POST("updatePersonalInfo")
    fun updatePersonalInfo(
        @Query("name") name: String,
        @Query("birthdate") date: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("additional_phone") additional_phone: String,
        @Query("city_id") city_id: Int,
        @Query("gender") gender: Int,
        @Query("main_address") main_address: String,
        @Query("additional_address") addresses: String
    ): Call<User>

    @GET("profile")
    fun getProfileInfo(): Call<ProfileResponse>

    @Multipart
    @POST("updateUserImage")
    fun updateProfileImage(
        @Part image : MultipartBody.Part
    ) : Call<ImageResponse>


    @POST("updateUserAddress")
    fun updateUserAdress(
        @Query("main_address") main_address : String,
        @Query("additional_address") additional_phone: String
    ) : Call<User>




    //tj.colibri.avrang.data.cities.Cities
    @GET("cities")
    fun getCitiesList(): Call<CitiesResponse>

    //Home
    @GET("home")
    fun getHome(): Call<HomeResponse>

    //GetProduct
    @GET("single/{slug}")
    fun getProduct(
        @Path("slug") slug: String
    ): Call<ProductInfortmation2>



    //tj.colibri.avrang.models.product.ProductInfo.Category
    //Catalog
    @GET("catalog")
    fun getCategories(): Call<CategoryResponse>


    // Filter
    @GET("products")
    fun getFilteredData(
        @Query("q") q : String? = null,
        @Query("priceFrom") priceFrom: Float ? = null,
        @Query("priceTo") priceTo: Float? = null,
        @Query("category") category: String? = null,
        @Query("page") page : Int? = 1,
        @Query("sort") sort : String? = null,
        @Query("attribute") attribute : String? = null,
        @Query("filter") filter : String? = null,
        @Query("brand") brand : String? = null
    ) : Call<CategoryProductRequest>


    //??????????????
    @POST("cart")
    fun updateCart(
        @Body json: CartIndexResponse
    ): Call<UpdateCart>

    @GET("order-details")
    fun orderDetails(): Call<OrderDetails>

    @POST("checkout")
    fun checkOut(
        @Body checkOut: CheckOutResquest
    ): Call<CheckOutResponse>

    @POST("take-loan")
    fun take_loan(
        @Body installment : PayClass
    ) : Call<Void>

    @POST("checkout")
    fun checkOutBank(
        @Body checkOut: CheckOutResquest
    ) : Call<CheckOutBankResponse>



    //Favorites
    @POST("add-to-favorite/{id}")
    fun addToFavorite(
        @Path("id") id: Int
    ): Call<String>

    @POST("delete-from-favorite/{id}")
    fun deleteFavorite(
        @Path("id") id: Int
    ): Call<String>

    @GET("profile/favorites")
    fun getFavorites(

    ): Call<tj.colibri.avrang.models.Favorite.FavoriteRequest>

    @GET("profile/orders")
    fun getMyOrders(

    ): Call<MyOrdersRequest>

    //BANKS
    @GET("banks")
    fun getBanks(
    ) : Call<InstallmentBanks>

    //FAQ
    @GET("faq")
    fun getAllFAQs() : Call<FAQRequest>


    //Contacts
    @GET("contacts")
    fun getContacts() : Call<ContactResponse>
}