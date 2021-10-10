package ru.androidlearning.presentation.fragments.history

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.androidlearning.core.DataLoadingState
import ru.androidlearning.core.DictionaryPresentationDataModel
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
        dataLoadingLiveData.postValue(DataLoadingState.Loading())
    }

    private fun doOnGetHistorySuccess(dictionaryPresentationDataModel: DictionaryPresentationDataModel) {
        dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationDataModel))
    }

    private fun doOnGetHistoryError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(DataLoadingState.Error(e))
    }
}
