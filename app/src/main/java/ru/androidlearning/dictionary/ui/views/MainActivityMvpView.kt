package ru.androidlearning.dictionary.ui.views

import ru.androidlearning.dictionary.ui.DictionaryPresentationData

interface MainActivityMvpView {
    fun showTranslatedResult(dictionaryPresentationData: DictionaryPresentationData)
    fun showNoDataLabel()
    fun hideNoDataLabel()
    fun showError(errorMessage: String?)
    fun showProgressBar()
    fun hideProgressBar()
}
