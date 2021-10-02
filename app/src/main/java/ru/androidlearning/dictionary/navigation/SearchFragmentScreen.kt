package ru.androidlearning.dictionary.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.dictionary.ui.fragments.search.SearchFragment

class SearchFragmentScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        SearchFragment.newInstance()
}
