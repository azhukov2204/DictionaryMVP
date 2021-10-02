package ru.androidlearning.dictionary.ui.interactor

import ru.androidlearning.dictionary.ui.DictionaryPresentationData

interface Interactor {
    suspend fun getHistory(): DictionaryPresentationData
    suspend fun search(word: String, isOnline: Boolean): DictionaryPresentationData
}
