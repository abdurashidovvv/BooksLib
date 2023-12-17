package uz.abdurashidov.bookapp.presentation.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.GetAllBooksResponse
import uz.abdurashidov.bookapp.domain.repository.BookRepository
import uz.abdurashidov.bookapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val booksFlow = MutableStateFlow<Resource<GetAllBooksResponse?>>(Resource.Loading())

    suspend fun getAllBooks(token: String): MutableStateFlow<Resource<GetAllBooksResponse?>> {
        viewModelScope.launch {
            bookRepository.getAllBooks(token).catch {
                    booksFlow.value = Resource.Error("${it.message}")
                }.collectLatest {
                    if (it.isSuccessful) {
                        booksFlow.value = Resource.Success(it.body())
                    } else {
                        booksFlow.value = Resource.Error(it.message(), null)
                    }
                }
        }
        return booksFlow
    }
}