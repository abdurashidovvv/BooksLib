package uz.abdurashidov.bookapp.presentation.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentSplashScreenBinding
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.presentation.register.AuthViewModel
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MySplashScreenFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentSplashScreenBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MySharedPreference.init(binding.root.context)
        val token=MySharedPreference.token
        Log.d("token", "onCreateView: $token")
        val handler= Handler(Looper.getMainLooper())
        val runnable=Runnable{
            if (token!=""){
                findNavController().navigate(R.id.homeFragment)
            }else{
                findNavController().navigate(R.id.mainRegisterFragment)
            }
        }
        handler.postDelayed(runnable, 3000)

        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}