package uz.abdurashidov.bookapp.domain.repository

import kotlinx.coroutines.flow.flow
import uz.abdurashidov.bookapp.data.remote.ApiService
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.GetAllBooksResponse
import uz.abdurashidov.bookapp.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllBooks(apiKey: String) =
        flow { emit(apiService.getAllBooks("Bearer " + apiKey)) }
}