package uz.abdurashidov.bookapp.presentation.main_menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.domain.model.get_all_category_response.AllCategoryResponse
import uz.abdurashidov.bookapp.domain.repository.CategoryRepository
import uz.abdurashidov.bookapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val allCategoryFlow =
        MutableStateFlow<Resource<AllCategoryResponse?>>(Resource.Loading())

    suspend fun getAllCategory(token: String): MutableStateFlow<Resource<AllCategoryResponse?>> {
        viewModelScope.launch {
            categoryRepository.getAllCategory(apiKey = token)
                .catch {
                    allCategoryFlow.value = Resource.Error("${it.message}", null)
                }
                .collectLatest {
                    if (it.isSuccessful) {
                        allCategoryFlow.value = Resource.Success(it.body())
                    } else {
                        allCategoryFlow.value = Resource.Error("${it.message()}")
                    }
                }
        }
        return allCategoryFlow
    }
}