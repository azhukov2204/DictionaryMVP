package ru.androidlearning.dictionary.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.fragments.details.DetailsFragment

class DetailsFragmentScreen(private val translatedWord: DictionaryPresentationData.TranslatedWord) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        DetailsFragment.newInstance(translatedWord)
}
