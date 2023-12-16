package uz.abdurashidov.bookapp.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.domain.model.author_response.login.LoginResponse
import uz.abdurashidov.bookapp.domain.model.author_response.login.LoginUserData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenResponse
import uz.abdurashidov.bookapp.domain.repository.AuthRepository
import uz.abdurashidov.bookapp.utils.Resource
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val registerFlow = MutableStateFlow<Resource<GetTokenResponse?>>(Resource.Loading())
    suspend fun register(
        getTokenData: GetTokenData
    ): MutableStateFlow<Resource<GetTokenResponse?>> {
        Log.d("@@@", "register: Ishlayabdi")
        viewModelScope.launch {

            authRepository.register(getTokenData).collect {
                Log.d("@@@1", "register: ${it.body()}")
                if (it.isSuccessful) {
                    registerFlow.emit(Resource.Success(it.body()))
                } else {
                    registerFlow.emit(Resource.Error(it.message(), null))
                }
            }

        }
        return registerFlow
    }


    private val loginFlow = MutableStateFlow<Resource<LoginResponse?>>(Resource.Loading())
    suspend fun login(
        loginUserData: LoginUserData
    ): MutableStateFlow<Resource<LoginResponse?>> {
        Log.d("@@@", "register: Ishlayabdi")
        viewModelScope.launch {

            authRepository.login(loginUserData).collect {
                Log.d("@@@1", "register: ${it.body()}")
                if (it.isSuccessful) {
                    loginFlow.emit(Resource.Success(it.body()))
                } else {
                    loginFlow.emit(Resource.Error(it.message(), null))
                }
            }

        }
        return loginFlow
    }


}