package uz.abdurashidov.bookapp.presentation.splash_screen

import android.annotation.SuppressLint
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.databinding.FragmentSplashScreenBinding
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.presentation.register.AuthViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MySplashScreenFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentSplashScreenBinding.inflate(layoutInflater) }
    private val authViewModel:AuthViewModel by viewModels()
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
            authViewModel.register(getTokenData).collect{
                Log.d("@@@", "onCreateView: $it")
            }
        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}