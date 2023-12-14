package uz.abdurashidov.bookapp.domain.model.author_response

data class Books(
    val category: Category,
    val description: String,
    val files: Files,
    val id: Int,
    val name: String,
    val photo: String,
    val sub_categories: List<SubCategory>
)