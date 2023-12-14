package uz.abdurashidov.bookapp.domain.model.book_list_response

data class Data(
    val author: Author,
    val category: Category,
    val description: String,
    val files: Files,
    val id: Int,
    val name: String,
    val photo: String,
    val sub_categories: List<SubCategory>
)