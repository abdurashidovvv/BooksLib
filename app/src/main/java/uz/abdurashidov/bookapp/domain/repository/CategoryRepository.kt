package uz.abdurashidov.bookapp.domain.repository

import kotlinx.coroutines.flow.flow
import uz.abdurashidov.bookapp.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllCategory(apiKey: String) =
        flow { emit(apiService.getAllCategories(token = "Bearer " + apiKey)) }
}