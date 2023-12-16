package uz.abdurashidov.bookapp.domain.model.author_response.login

data class LoginResponse(
    val `data`: Data,
    val message: Any,
    val status: String
)