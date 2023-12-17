package uz.abdurashidov.bookapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.abdurashidov.bookapp.domain.model.author_response.login.LoginResponse
import uz.abdurashidov.bookapp.domain.model.author_response.login.LoginUserData
import uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse.GetAllAuthorsResponse
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.GetAllBooksResponse
import uz.abdurashidov.bookapp.domain.model.get_all_category_response.AllCategoryResponse
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenResponse

interface ApiService {

    @POST("/api/register")
    suspend fun registerUser(
        @Body data: GetTokenData
    ): Response<GetTokenResponse>

    @POST("/api/login")
    suspend fun loginUser(
        @Body data: LoginUserData
    ): Response<LoginResponse>


    @GET("api/categories")
    suspend fun getAllCategories(
        @Header("Authorization") token: String
    ): Response<AllCategoryResponse>

    @GET("api/books")
    suspend fun getAllBooks(
        @Header("Authorization") token: String
    ): Response<GetAllBooksResponse>
    @GET("api/authors")
    suspend fun getAllAuthors(
        @Header("Authorization") token: String
    ): Response<GetAllAuthorsResponse>

    companion object {
        val BASE_URL = "https://bookrate.botuzrobot.uz/"
    }
}