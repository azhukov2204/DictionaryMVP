package ru.androidlearning.dictionary.ui.base_abstract_templates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

abstract class BaseMVVMViewModel : ViewModel() {

    protected val dataLoadingLiveData = MutableLiveData<DataLoadingState<DictionaryPresentationData>>()
    fun getLiveData(): LiveData<DataLoadingState<DictionaryPresentationData>> = dataLoadingLiveData

    protected val coroutineScopeIO by lazy {
        CoroutineScope(
            Dispatchers.IO
                    + SupervisorJob()
                    + CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }
        )
    }

    override fun onCleared() {
        coroutineScopeIO.cancel()
        super.onCleared()
    }
}
