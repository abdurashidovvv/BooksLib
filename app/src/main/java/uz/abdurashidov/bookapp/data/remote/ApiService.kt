package uz.abdurashidov.bookapp.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenResponse

interface ApiService {

    @POST("/api/register")
    suspend fun registerUser(
        @Body data:GetTokenData
    ):Response<GetTokenResponse>


    companion object {
        val BASE_URL="https://najot-hakaton-api.botuzrob"
    }
}