package uz.abdurashidov.bookapp.presentation.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.data.local.MySharedPreference
import uz.abdurashidov.bookapp.databinding.FragmentRegisterBinding
import uz.abdurashidov.bookapp.domain.model.token_request.GetTokenData
import uz.abdurashidov.bookapp.utils.Resource
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class RegisterFragment : Fragment(), CoroutineScope {

    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding.registerBtn.setOnClickListener {
            launch(Dispatchers.Main) {
                authViewModel.register(
                    GetTokenData(
                        name = binding.fishEdt.text.toString(),
                        email = binding.emailEdt.text.toString(),
                        password = binding.passwordEdt.text.toString(),
                        password_confirmation = binding.repasswordEdt.text.toString()
                    )
                ).collect {
                    when (it) {
                        is Resource.Error -> Toast.makeText(context, "Xatolik", Toast.LENGTH_SHORT)
                            .show()

                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            MySharedPreference.init(binding.root.context)
                            MySharedPreference.token = it.data?.data?.token
                            findNavController().navigate(R.id.homeFragment)
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