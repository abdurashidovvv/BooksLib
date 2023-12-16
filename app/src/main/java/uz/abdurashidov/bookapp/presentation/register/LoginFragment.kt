package uz.abdurashidov.bookapp.presentation.register

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
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentLoginBinding
import uz.abdurashidov.bookapp.domain.model.author_response.login.LoginUserData
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class LoginFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val loginUserData = LoginUserData(
            email = binding.emailEdt.text.toString(),
            password = binding.passwordEdt.text.toString()
        )
        binding.registerBtn.setOnClickListener {

            launch(Dispatchers.Main) {
                authViewModel.login(
                    LoginUserData(
                        email = binding.emailEdt.text.toString(),
                        password = binding.passwordEdt.text.toString()
                    )
                ).collect {
                    when (it) {
                        is Resource.Error -> {
                            Toast.makeText(context, "Xatolik", Toast.LENGTH_SHORT).show()
                        }

                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            MySharedPreference.init(binding.root.context)
                            MySharedPreference.token = it.data?.data?.token
                            binding.progressBar.visibility = View.GONE

                        }
                    }
                }
            }
        }
        return binding.root
    }

    override val coroutineContext: CoroutineContext
        get() = Job()
}