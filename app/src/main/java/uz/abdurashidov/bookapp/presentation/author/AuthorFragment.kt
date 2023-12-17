package uz.abdurashidov.bookapp.presentation.author

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
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentAuthorBinding
import uz.abdurashidov.bookapp.domain.model.get_all_authors_reponse.Data
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class AuthorFragment : Fragment(), CoroutineScope, AuthorAdapter.AuthorOnClickInterface {
    private val binding by lazy { FragmentAuthorBinding.inflate(layoutInflater) }
    private val authorViewModel: AuthorViewModel by viewModels()
    private lateinit var authorAdapter: AuthorAdapter
    private lateinit var list: ArrayList<Data>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        list = ArrayList()
        authorAdapter = AuthorAdapter(list, this)
        binding.authorListRv.adapter=authorAdapter

        MySharedPreference.init(binding.root.context)
        val token = MySharedPreference.token
        launch(Dispatchers.Main) {
            authorViewModel.getAllAuthors("$token").collectLatest {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(context, "Xatolik", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        list.addAll(it.data?.data!!)
                        authorAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()

    override fun AuthorOnClick(authorResponseItem: Data) {

    }
}