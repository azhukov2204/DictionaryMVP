package ru.androidlearning.presentation.fragments.search.dialog

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.androidlearning.core.DataLoadingState
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.core.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.presentation.interactor.Interactor

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

    private fun doOnSearchSuccess(dictionaryPresentationDataModel: DictionaryPresentationDataModel) {
        dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationDataModel))
    }

    private fun doOnSearchError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(DataLoadingState.Error(e))
    }
}
