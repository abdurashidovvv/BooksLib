package uz.abdurashidov.bookapp.presentation.author

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.databinding.FragmentAuthorBinding

class AuthorFragment : Fragment() {

    private val binding by lazy { FragmentAuthorBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}