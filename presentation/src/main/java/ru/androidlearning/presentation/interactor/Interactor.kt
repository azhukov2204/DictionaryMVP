package ru.androidlearning.presentation.interactor

interface Interactor {
    suspend fun getHistory(): ru.androidlearning.core.DictionaryPresentationData
    suspend fun search(word: String, isOnline: Boolean): ru.androidlearning.core.DictionaryPresentationData
}
