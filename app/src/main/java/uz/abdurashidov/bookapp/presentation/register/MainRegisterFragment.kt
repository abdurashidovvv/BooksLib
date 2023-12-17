package uz.abdurashidov.bookapp.presentation.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentMainRegisterBinding
import uz.abdurashidov.bookapp.presentation.main_menu.CategoryViewModel
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext


class MainRegisterFragment : Fragment(), CoroutineScope {

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

    override val coroutineContext: CoroutineContext
        get() = Job()
}