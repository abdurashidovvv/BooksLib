package uz.abdurashidov.bookapp.presentation.homefragment

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
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.databinding.FragmentHomeBinding
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.presentation.author.AuthorFragment
import uz.abdurashidov.bookapp.presentation.books.BookListFragment
import uz.abdurashidov.bookapp.presentation.main_menu.MainMenuFragment
import uz.abdurashidov.bookapp.presentation.register.AuthViewModel
import uz.abdurashidov.bookapp.presentation.save_book.SavedBooksFragment
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class HomeFragment : Fragment(), CoroutineScope {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val getTokenData = GetTokenData(
            name = "Saydullo",
            email = "abdurashidovsaydullo35@gmail.com",
            password = "0510",
            password_confirmation = "0510"
        )

        launch {
            authViewModel.register(getTokenData)
            Log.d("@@@", "onCreateView: ${authViewModel.registerFlow.data}")
        }
        val fragment = MainMenuFragment()
        replaceFragment(fragment)

        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val fragment = MainMenuFragment()
                    replaceFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.book -> {
                    val fragment = BookListFragment()
                    replaceFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.author -> {
                    val fragment = AuthorFragment()
                    replaceFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.saved -> {
                    val fragment = SavedBooksFragment()
                    replaceFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    override val coroutineContext: CoroutineContext
        get() = Job()

}