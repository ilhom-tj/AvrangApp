package tj.colibri.avrang.network

import tj.colibri.avrang.data.ApiData.chekout.forRequest.CheckOutResquest
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.*
import tj.colibri.avrang.data.ApiData.Cart.CartIndexResponse
import tj.colibri.avrang.data.ApiData.Cart.UpdateCart
import tj.colibri.avrang.data.ApiData.Category.CategoryResponse
import tj.colibri.avrang.data.ApiData.chekout.forRequest.CheckOutResponse
import tj.colibri.avrang.data.ApiData.cities.CitiesResponse
import tj.colibri.avrang.data.ApiData.registration.ConfirmCode
import tj.colibri.avrang.data.ApiData.registration.RegistrationCallBack
import tj.colibri.avrang.data.ApiData.home.HomeResponse
import tj.colibri.avrang.data.ApiData.orderDetails.OrderDetails
import tj.colibri.avrang.data.ApiData.product.ProductInfo.ProductInfortmation2
import tj.colibri.avrang.data.ApiData.profile.ProfileResponse
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

    @POST("auth/number-check")
    fun firstStepRegister(
        @Query("phone") phone: String
    ): Call<ConfirmCode>


    @POST("auth/number-check")
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


    //tj.colibri.avrang.data.ApiData.orderDetails.User settings
    @POST("updatePersonalInfo")
    fun updatePersonalInfo(
        @Query("name") name: String,
        @Query("birthdate") date: String,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("additional_phone") additional_phone: String,
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
    fun getProduct(@Path("slug") slug: String): Call<ProductInfortmation2>



    //tj.colibri.avrang.data.ApiData.product.ProductInfo.Category
    //Catalog
    @GET("catalog")
    fun getCategories() : Call<CategoryResponse>


    //Корзина
    @POST("cart")
    fun updateCart(
        @Body json : CartIndexResponse
    ) : Call<UpdateCart>

    @GET("order-details")
    fun orderDetails() : Call<OrderDetails>

    @POST("checkout")
    fun checkOut(@Body checkOut : CheckOutResquest) : Call<CheckOutResponse>


    //Favorites
    @POST("add-to-favorite/{id}")
    fun addToFavorite(@Path("id")id : Int) : Call<String>

    @POST("delete-from-favorite/{id}")
    fun deleteFavorite(@Path("id") id:Int) : Call<String>

    @GET("profile/favorites")
    fun getFavorites()  : Call<tj.colibri.avrang.data.ApiData.Favorite.FavoriteRequest>
}