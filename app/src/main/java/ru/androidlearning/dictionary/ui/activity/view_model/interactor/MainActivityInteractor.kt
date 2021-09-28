package ru.androidlearning.dictionary.ui.activity.view_model.interactor

import ru.androidlearning.dictionary.ui.DictionaryPresentationData

interface MainActivityInteractor {
    suspend fun translate(word: String, language: String): DictionaryPresentationData
}
