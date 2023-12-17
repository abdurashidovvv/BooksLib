package uz.abdurashidov.bookapp.presentation.main_menu

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
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainMenuFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentMainMenuBinding.inflate(layoutInflater) }
    private val categoryViewModel:CategoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MySharedPreference.init(binding.root.context)
        val token= MySharedPreference.token
        launch {
            categoryViewModel.getAllCategory("$token").collectLatest {
                when(it){
                    is Resource.Error -> {
                        Log.d("main_get_catergory", "onCreateView: ${it.message}")
                    }
                    is Resource.Loading ->{
                        Log.d("main_get_catergory", "onCreateView: ${it.message}")
                    }
                    is Resource.Success -> {
                        Log.d("main_get_catergory", "onCreateView: ${it.data}")
                    }
                }
            }
        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}