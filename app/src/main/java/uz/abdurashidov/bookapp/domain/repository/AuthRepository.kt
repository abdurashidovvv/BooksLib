package uz.abdurashidov.bookapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import uz.abdurashidov.bookapp.data.remote.ApiService
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun register(getTokenData: GetTokenData): Flow<Response<GetTokenResponse>> =
        flow { emit(apiService.registerUser(data = getTokenData)) }


}