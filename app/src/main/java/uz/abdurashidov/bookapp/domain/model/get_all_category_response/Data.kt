package uz.abdurashidov.bookapp.domain.model.get_all_category_response

data class Data(
    val id: Int,
    val name: String,
    val relationship: Relationship,
    val status: Int
)