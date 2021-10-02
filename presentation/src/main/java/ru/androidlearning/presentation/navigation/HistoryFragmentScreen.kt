package ru.androidlearning.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.presentation.fragments.history.HistoryFragment

class HistoryFragmentScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        HistoryFragment.newInstance()
}
