package uz.abdurashidov.bookapp.presentation.homefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.databinding.FragmentHomeBinding
import uz.abdurashidov.bookapp.presentation.author.AuthorFragment
import uz.abdurashidov.bookapp.presentation.books.BookListFragment
import uz.abdurashidov.bookapp.presentation.main_menu.MainMenuFragment
import uz.abdurashidov.bookapp.presentation.save_book.SavedBooksFragment

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

}