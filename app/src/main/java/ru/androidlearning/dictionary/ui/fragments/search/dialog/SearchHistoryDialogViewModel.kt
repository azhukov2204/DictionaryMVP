package ru.androidlearning.dictionary.ui.fragments.search.dialog

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.dictionary.ui.interactor.Interactor

class SearchHistoryDialogViewModel(
    private val interactor: Interactor
) : BaseMVVMViewModel() {

    fun search(word: String) {
        if (word.isNotBlank()) {
            coroutineScopeIO.launch(
                CoroutineExceptionHandler { _, throwable -> doOnSearchError(throwable) }
            ) {
                doOnSearchSuccess(interactor.search(word, false))
            }
        }
    }

    private fun doOnSearchSuccess(dictionaryPresentationData: DictionaryPresentationData) {
        dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationData))
    }

    private fun doOnSearchError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(DataLoadingState.Error(e))
    }
}
