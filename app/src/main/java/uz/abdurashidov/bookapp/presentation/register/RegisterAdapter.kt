package uz.abdurashidov.bookapp.presentation.register

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.abdurashidov.bookapp.presentation.onboarding_screen.OnboardingItemFragment

class RegisterAdapter(val list: List<String>, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment()
            1 -> LoginFragment()
            else -> RegisterFragment()
        }
    }
}