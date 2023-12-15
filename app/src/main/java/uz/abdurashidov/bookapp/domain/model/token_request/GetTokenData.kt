package uz.abdurashidov.bookapp.domain.model.token_request

import androidx.annotation.Keep

@Keep
data class GetTokenData(
    val name: String,
    val email:String,
    val password:String,
    val password_confirmation:String
)