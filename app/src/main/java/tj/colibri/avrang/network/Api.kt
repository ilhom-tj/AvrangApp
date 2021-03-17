package tj.colibri.avrang.network

import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.*
import tj.colibri.avrang.data.ApiData.Cart.Cart
import tj.colibri.avrang.data.ApiData.Category.CategoryResponse
import tj.colibri.avrang.data.ApiData.cities.CitiesResponse
import tj.colibri.avrang.data.ApiData.registration.ConfirmCode
import tj.colibri.avrang.data.ApiData.registration.RegistrationCallBack
import tj.colibri.avrang.data.ApiData.home.HomeResponse
import tj.colibri.avrang.data.ApiData.product.ProductInformation
import tj.colibri.avrang.data.ApiData.profile.ProfileResponse
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.feedBack.FeedBack
import tj.colibri.avrang.data.ragistration.PhoneRegParams
import tj.colibri.avrang.data.user.User

interface Api {
    @POST("review")
    fun addReview(
        @Query("product_id") product_id: String,
        @Query("user_id") user_id: String,
        @Query("rating") rating: Int,
        @Query("body") body: String,
    ): Call<Comment>

    @POST("sendFeedback")
    fun sendFeedBack(
        @Query("name") name: String,
        @Query("phone") phone: String,
        @Query("message") message: String,
    ): Call<FeedBack>

    @POST("register")
    fun registerNewUser(
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
        @Query("phone") phone: String,
    ): Call<RegistrationCallBack>

    @POST("number-сheck")
    fun numberCheck(
        @Query("phone") phone: String,
        @Query("confirm_code") confirm_code: String
    ): Call<PhoneRegParams>


    //UpdateProfile
    @POST("updateUserAddress")
    fun updateUserAddress(
        @Query("addresses")
        addresses: String
    )

    //Auth

    @POST("auth/login")
    fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Call<RegistrationCallBack>

    @POST("auth/number-сheck")
    fun firstStepRegister(
        @Query("phone") phone: String
    ): Call<ConfirmCode>


    @POST("auth/number-сheck")
    fun secondtStepRegister(
        @Query("phone") phone: String,
        @Query("confirm_code") confirm_code: String
    ): Call<ConfirmCode>


    @POST("auth/register")
    fun registration(
        @Query("password") password: String,
        @Query("password_confirmation") password_confirmation: String,
        @Query("phone") phone: String
    ): Call<RegistrationCallBack>

    //GET EVERYTHING FROM HOME
    @GET("home")
    fun homeProducts(): Call<HomeResponse>


    //User settings
    @POST("updatePersonalInfo")
    fun updatePersonalInfo(
        @Query("name") name: String,
        @Query("birthdate") date: String,
        @Query("phone") phone: String,
        @Query("email") email: String?,
        @Query("additional_phone") additional_phone: String?,
        @Query("city_id") city_id: Int,
        @Query("gender") gender: Int,
        @Query("addresses") addresses: String?
    ): Call<User>

    @GET("profile")
    fun getProfileInfo(): Call<ProfileResponse>


    //tj.colibri.avrang.data.cities.Cities
    @GET("cities")
    fun getCitiesList(): Call<CitiesResponse>

    //Home
    @GET("home")
    fun getHome(): Call<HomeResponse>

    //GetProduct
    @GET("single/{slug}")
    fun getProduct(@Path("slug") slug: String): Call<ProductInformation>


    //Cart

    @GET("cart")
    fun getCart(): Call<Cart>

    @POST("add-to-cart/{id}")
    fun addToCart(
        @Path("id") id: Int,
        @Query("quantity") quantity: Int
    ): Call<Cart>

    //Category
    @GET("catalog")
    fun getCategories() : Call<CategoryResponse>

    @GET("catalog")
    fun getSubCategory(@Path("slug") slug: String) : Call<CategoryResponse>

}