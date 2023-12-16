package uz.abdurashidov.bookapp.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.databinding.FragmentMainRegisterBinding


class MainRegisterFragment : Fragment() {

    private val binding by lazy { FragmentMainRegisterBinding.inflate(layoutInflater) }
    private lateinit var registerAdapter: RegisterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerAdapter = RegisterAdapter(list, this)
        binding.viewPager2.adapter = registerAdapter

        TabLayoutMediator(
            binding.registerTabLayout,
            binding.viewPager2
        ) { tab, position ->
            tab.text = list[position]
        }.attach()

        return binding.root
    }

    companion object {
        val list = listOf(
            "Ro'yhatdan o'tish",
            "Kirish"
        )
    }
}