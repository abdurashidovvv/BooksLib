package uz.abdurashidov.bookapp.presentation.onboarding_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(val list: List<OnboardingData>, fragment: Fragment)
    : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardingItemFragment.newInstance("$position")
    }
}