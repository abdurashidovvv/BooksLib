package uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse

data class Book(
    val category: Category,
    val description: String,
    val files: Files,
    val id: Int,
    val likes_count: Int,
    val name: String,
    val photo: String,
    val storage_count: Int,
    val sub_categories: List<SubCategory>
)