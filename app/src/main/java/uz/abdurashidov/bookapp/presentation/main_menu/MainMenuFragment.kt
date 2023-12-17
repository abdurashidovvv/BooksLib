package uz.abdurashidov.bookapp.presentation.main_menu

import BookListAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentMainMenuBinding
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.Data
import uz.abdurashidov.bookapp.presentation.author.AuthorAdapter
import uz.abdurashidov.bookapp.presentation.author.AuthorViewModel
import uz.abdurashidov.bookapp.presentation.books.BookViewModel
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainMenuFragment : Fragment(), CoroutineScope, BookListAdapter.RvClick,
    AuthorAdapter.AuthorOnClickInterface {

    private val binding by lazy { FragmentMainMenuBinding.inflate(layoutInflater) }
    private val bookViewModel: BookViewModel by viewModels()
    private val authorViewModel: AuthorViewModel by viewModels()
    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var authorAdapter: AuthorAdapter
    private lateinit var list: ArrayList<Data>
    private lateinit var authorList: ArrayList<uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse.Data>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        MySharedPreference.init(binding.root.context)
        val token = MySharedPreference.token

        list = ArrayList()
        authorList = ArrayList()
        authorAdapter = AuthorAdapter(authorList, this)
        bookListAdapter = BookListAdapter(list, this)
        binding.authorBooksRv.adapter = authorAdapter
        binding.famousBooksRv.adapter = bookListAdapter

        launch {
            bookViewModel.getAllBooks("$token").collectLatest {
                when (it) {
                    is Resource.Error -> {
                        Log.d("main_get_catergory", "onCreateView: ${it.message}")
                    }

                    is Resource.Loading -> {
                        Log.d("main_get_catergory", "onCreateView: ${it.message}")
                    }

                    is Resource.Success -> {
                        list.addAll(it.data?.data!!)
                        bookListAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

        launch {
            authorViewModel.getAllAuthors("$token").collectLatest {
                when (it) {
                    is Resource.Error -> {
                        Log.d("main_get_catergory", "onCreateView: ${it.message}")
                    }

                    is Resource.Loading -> {
                        Log.d("main_get_catergory", "onCreateView: ${it.message}")
                    }

                    is Resource.Success -> {
                        authorList.addAll(it.data?.data!!)
                        authorAdapter .notifyDataSetChanged()
                    }
                }
            }
        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()

    override fun itemClick(text: String) {

    }

    override fun AuthorOnClick(authorResponseItem: uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse.Data) {

    }
}