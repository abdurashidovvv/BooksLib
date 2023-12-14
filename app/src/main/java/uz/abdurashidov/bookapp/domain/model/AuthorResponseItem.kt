package uz.abdurashidov.bookapp.domain.model

data class AuthorResponseItem(
    val birth_country: String,
    val birth_date: String,
    val books: Books,
    val created_at: String,
    val death_date: Any,
    val description: Any,
    val fullName: String,
    val id: Int,
    val photo: String,
    val updated_at: String
)