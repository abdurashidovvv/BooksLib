package uz.abdurashidov.bookapp.presentation.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenResponse
import uz.abdurashidov.bookapp.domain.repository.AuthRepository
import uz.abdurashidov.bookapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val registerFlow = MutableStateFlow<Resource<GetTokenResponse?>>(Resource.Loading())

    suspend fun register(
        getTokenData: GetTokenData
    ): MutableStateFlow<Resource<GetTokenResponse?>> {
        coroutineScope {
            authRepository.register(getTokenData).collect {
                if (it.isSuccessful) {
                    registerFlow.emit(Resource.Success(it.body()))
                } else {
                    registerFlow.emit(Resource.Error(it.message(), null))
                }
            }
        }
        return registerFlow
    }
}