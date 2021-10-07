package ru.androidlearning.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.presentation.fragments.stopwatch.StopwatchFragment

class StopwatchFragmentScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        StopwatchFragment.newInstance()
}
