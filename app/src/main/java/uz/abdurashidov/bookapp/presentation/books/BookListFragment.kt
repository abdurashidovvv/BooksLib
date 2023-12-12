package uz.abdurashidov.bookapp.presentation.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.abdurashidov.bookapp.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {

    private val binding by lazy { FragmentBookListBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}

