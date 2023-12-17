package uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse

data class Data(
    val birth_country: String,
    val birthday_date: String,
    val books: List<Book>,
    val death_date: Any,
    val description: String,
    val fullname: String,
    val id: Int,
    val photo: String
)