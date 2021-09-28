package ru.androidlearning.dictionary.ui.fragments.history

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.dictionary.ui.interactor.Interactor

class HistoryFragmentViewModel(
    private val interactor: Interactor
) : BaseMVVMViewModel() {

    fun getHistory() {
        doBeforeGetHistory()
        coroutineScopeIO.launch(
            CoroutineExceptionHandler { _, throwable -> doOnGetHistoryError(throwable) }
        ) {
            doOnGetHistorySuccess(interactor.getHistory())
        }
    }

    private fun doBeforeGetHistory() {
        dataLoadingLiveData.postValue(DataLoadingState.Loading())
    }

    private fun doOnGetHistorySuccess(dictionaryPresentationData: DictionaryPresentationData) {
        dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationData))
    }

    private fun doOnGetHistoryError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(DataLoadingState.Error(e))
    }
}
