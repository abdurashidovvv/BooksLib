package uz.abdurashidov.bookapp.presentation.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse.GetAllAuthorsResponse
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.GetAllBooksResponse
import uz.abdurashidov.bookapp.domain.repository.AuthorRepository
import uz.abdurashidov.bookapp.domain.repository.BookRepository
import uz.abdurashidov.bookapp.utils.Resource
import javax.inject.Inject
@HiltViewModel
class AuthorViewModel @Inject constructor(
    private val authorRepository: AuthorRepository
) : ViewModel() {

    private val authorsFlow = MutableStateFlow<Resource<GetAllAuthorsResponse?>>(Resource.Loading())

    suspend fun getAllAuthors(token: String): MutableStateFlow<Resource<GetAllAuthorsResponse?>> {
        viewModelScope.launch {
            authorRepository.getAllAuthors(token).catch {
                authorsFlow.value = Resource.Error("${it.message}")
            }.collectLatest {
                if (it.isSuccessful) {
                    authorsFlow.value = Resource.Success(it.body())
                } else {
                    authorsFlow.value = Resource.Error(it.message(), null)
                }
            }
        }
        return authorsFlow
    }
}