package uz.abdurashidov.bookapp.presentation.onboarding_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.abdurashidov.bookapp.R
import uz.abdurashidov.bookapp.databinding.FragmentOnboardingBinding

@Suppress("UNUSED_CHANGED_VALUE")
class OnboardingFragment : Fragment() {

    private val binding by lazy { FragmentOnboardingBinding.inflate(layoutInflater) }
    private lateinit var onboardingAdapter: OnboardingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        onboardingAdapter = OnboardingAdapter(list, this)
        binding.viewPager2.adapter = onboardingAdapter

        binding.nextBtn.setOnClickListener {
            // Change the current fragment to the next one
            if (binding.viewPager2.currentItem != 2) {
                binding.viewPager2.setCurrentItem(
                    (binding.viewPager2.currentItem + 1),
                    true
                )
            } else {
                findNavController().navigate(R.id.mainRegisterFragment)

            }
        }
        return binding.root
    }

    companion object {
        val list = listOf(
            OnboardingData(
                title = "Read anytime anywhere",
                infoText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore.",
                img = R.drawable.onboarding_img1
            ),
            OnboardingData(
                title = "Read anytime anywhere",
                infoText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore.",
                img = R.drawable.onboarding_img2
            ),
            OnboardingData(
                title = "Read anytime anywhere",
                infoText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore.",
                img = R.drawable.onboarding_img3
            ),
        )
    }
}

data class OnboardingData(
    var title: String,
    var infoText: String,
    var img: Int
)