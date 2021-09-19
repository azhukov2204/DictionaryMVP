package ru.androidlearning.mvpdictionary.ui.views

import ru.androidlearning.mvpdictionary.ui.DictionaryPresentationData

interface MainActivityMvpView {
    fun showTranslatedResult(dictionaryPresentationData: DictionaryPresentationData)
    fun showNoDataLabel()
    fun hideNoDataLabel()
    fun showError(errorMessage: String?)
    fun showProgressBar()
    fun hideProgressBar()
}
