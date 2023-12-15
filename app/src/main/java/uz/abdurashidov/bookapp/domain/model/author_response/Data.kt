package uz.abdurashidov.bookapp.domain.model.author_response

data class Data(
    val birth_country: String,
    val birthday_date: String,
    val books: List<Book>,
    val death_date: String,
    val description: Any,
    val fullname: String,
    val id: Int,
    val photo: String
)