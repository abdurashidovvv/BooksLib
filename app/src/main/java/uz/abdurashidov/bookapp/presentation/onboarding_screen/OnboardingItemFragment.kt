package uz.abdurashidov.bookapp.presentation.onboarding_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.databinding.FragmentOnboardingBinding
import uz.abdurashidov.bookapp.databinding.FragmentOnboardingItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    private val binding by lazy { FragmentOnboardingItemBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        when (param1) {
            "0" -> {
                binding.onboarding1Title.text = OnboardingFragment.list[0].title
                binding.onboarding1Info.text = OnboardingFragment.list[0].infoText
                binding.onboarding1Img.setImageResource(OnboardingFragment.list[0].img)
            }
            "1" -> {
                binding.onboarding1Title.text = OnboardingFragment.list[1].title
                binding.onboarding1Info.text = OnboardingFragment.list[1].infoText
                binding.onboarding1Img.setImageResource(OnboardingFragment.list[1].img)
            }
            "2" -> {
                binding.onboarding1Title.text = OnboardingFragment.list[2].title
                binding.onboarding1Info.text = OnboardingFragment.list[2].infoText
                binding.onboarding1Img.setImageResource(OnboardingFragment.list[2].img)
                FragmentOnboardingBinding.inflate(layoutInflater).apply {
                    nextBtn.text = "Start"
                }
            }

        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment OnboardingItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            OnboardingItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}