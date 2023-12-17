package uz.abdurashidov.bookapp.domain.model.get_all_books_response

data class Comment(
    val description: String,
    val id: Int,
    val rating: Int,
    val username: String
)