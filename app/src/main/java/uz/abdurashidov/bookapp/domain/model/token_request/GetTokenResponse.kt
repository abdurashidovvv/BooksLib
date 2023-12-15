package uz.abdurashidov.bookapp.domain.model.token_request

data class GetTokenResponse(
    val `data`: Data,
    val message: Any,
    val status: String
)