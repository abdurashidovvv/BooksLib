package uz.abdurashidov.bookapp.presentation.books

import BookListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentBookListBinding
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.Data
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class BookListFragment : Fragment(), CoroutineScope, BookListAdapter.RvClick {

    private val binding by lazy { FragmentBookListBinding.inflate(layoutInflater) }
    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var list: ArrayList<Data>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        list = ArrayList()
        bookListAdapter = BookListAdapter(list, this)
        binding.bookListRv.adapter = bookListAdapter
        MySharedPreference.init(binding.root.context)
        val token = MySharedPreference.token
        launch(Dispatchers.Main) {
            bookViewModel.getAllBooks("$token").collectLatest {
                when (it) {
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Xatolik", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        list.addAll(it.data?.data!!)
                        bookListAdapter.notifyDataSetChanged()
                        binding.progressBar.visibility=View.GONE
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
}

