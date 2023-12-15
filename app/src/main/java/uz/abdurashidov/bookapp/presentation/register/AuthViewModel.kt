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

    private val _registerFlow = MutableStateFlow<Resource<GetTokenResponse?>>(Resource.Loading())
    val registerFlow = _registerFlow.value
    suspend fun register(
        getTokenData: GetTokenData
    ) {
        coroutineScope {
            authRepository.register(getTokenData).collect {
                if (it.isSuccessful) {
                    _registerFlow.emit(Resource.Success(it.body()))
                } else {
                    _registerFlow.emit(Resource.Error(it.message(), null))
                }
            }
        }
    }
}