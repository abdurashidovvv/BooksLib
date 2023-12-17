package uz.abdurashidov.bookapp.domain.repository

import kotlinx.coroutines.flow.flow
import uz.abdurashidov.bookapp.data.remote.ApiService
import javax.inject.Inject

class AuthorRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllAuthors(apiKey: String) =
        flow { emit(apiService.getAllAuthors("Bearer " + apiKey)) }
}