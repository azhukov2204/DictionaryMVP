package ru.androidlearning.presentation.fragments.history

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.androidlearning.core.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.presentation.interactor.Interactor

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
        dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Loading())
    }

    private fun doOnGetHistorySuccess(dictionaryPresentationData: ru.androidlearning.core.DictionaryPresentationData) {
        dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Success(dictionaryPresentationData))
    }

    private fun doOnGetHistoryError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Error(e))
    }
}
