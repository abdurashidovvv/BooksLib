package uz.abdurashidov.bookapp.domain.model.get_all_books_response

data class Data(
    val author: Author,
    val average_rating: String,
    val category: Category,
    val comments: List<Comment>,
    val description: String,
    val files: Files,
    val id: Int,
    val likes_count: Int,
    val name: String,
    val photo: String,
    val storage_count: Int,
    val sub_categories: List<SubCategory>
)