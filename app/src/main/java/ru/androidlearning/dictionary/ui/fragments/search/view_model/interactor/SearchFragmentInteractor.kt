package ru.androidlearning.dictionary.ui.fragments.search.view_model.interactor

import ru.androidlearning.dictionary.ui.DictionaryPresentationData

interface SearchFragmentInteractor {
    suspend fun search(word: String): DictionaryPresentationData
}
