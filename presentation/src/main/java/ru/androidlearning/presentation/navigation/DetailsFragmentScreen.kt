package ru.androidlearning.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.presentation.fragments.details.DetailsFragment

class DetailsFragmentScreen(private val translatedWord: DictionaryPresentationDataModel.TranslatedWord) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        DetailsFragment.newInstance(translatedWord)
}
