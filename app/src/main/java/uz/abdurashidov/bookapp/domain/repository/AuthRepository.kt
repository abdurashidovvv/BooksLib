package uz.abdurashidov.bookapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import uz.abdurashidov.bookapp.data.remote.ApiService
import uz.abdurashidov.bookapp.domain.model.author_response.login.LoginUserData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun register(getTokenData: GetTokenData) =
        flow { emit(apiService.registerUser(data = getTokenData)) }

    suspend fun login(loginUserData: LoginUserData) =
        flow { emit(apiService.loginUser(loginUserData)) }

}