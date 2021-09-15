package ru.androidlearning.mvpdictionary.ui.views

import ru.androidlearning.mvpdictionary.ui.DictionaryPresentationData

interface MainActivityView {
    fun showTranslatedResult(dictionaryPresentationData: DictionaryPresentationData)
    fun showNoDataLabel()
    fun hideNoDataLabel()
    fun showError(errorMessage: String?)
    fun showProgressBar()
    fun hideProgressBar()
}
