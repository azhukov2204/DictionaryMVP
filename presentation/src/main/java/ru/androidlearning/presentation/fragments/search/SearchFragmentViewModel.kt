package ru.androidlearning.presentation.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.androidlearning.core.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.presentation.interactor.Interactor
import ru.androidlearning.utils.network.NetworkState
import ru.androidlearning.utils.network.NetworkStateMonitor

private const val SAVED_TRANSLATED_DATA_KEY = "SavedTranslatedData"

class SearchFragmentViewModel(
    private val interactor: Interactor,
    private val networkStateMonitor: NetworkStateMonitor,
    private val savedStateHandle: SavedStateHandle,
) : BaseMVVMViewModel() {

    private val _networkStateLiveData = MutableLiveData<ru.androidlearning.core.DataLoadingState<NetworkState>>()
    val networkStateLiveData: LiveData<ru.androidlearning.core.DataLoadingState<NetworkState>>
        get() = _networkStateLiveData

    private var currentNetworkState: NetworkState = NetworkState.DISCONNECTED

    init {
        startNetworkStateMonitoring()
    }

    private fun startNetworkStateMonitoring() {
        coroutineScopeIO.launch(
            CoroutineExceptionHandler { _, throwable -> doOnGetNetworkStatusError(throwable) }
        ) {
            for (state in networkStateMonitor.channel) {
                doOnGetNetworkStatusSuccess(state)
            }
        }
        networkStateMonitor.startMonitoring()
    }

    fun search(word: String) {
        if (word.isNotBlank()) {
            doBeforeTranslate()
            coroutineScopeIO.launch(
                CoroutineExceptionHandler { _, throwable -> doOnTranslateError(throwable) }
            ) {
                doOnTranslateSuccess(interactor.search(word, currentNetworkState == NetworkState.CONNECTED))
            }
        }
    }

    private fun doBeforeTranslate() {
        dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Loading())
    }

    private fun doOnTranslateSuccess(dictionaryPresentationData: ru.androidlearning.core.DictionaryPresentationData) {
        dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Success(dictionaryPresentationData))
        savedStateHandle[SAVED_TRANSLATED_DATA_KEY] = dictionaryPresentationData
    }

    private fun doOnTranslateError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Error(e))
    }

    private fun doOnGetNetworkStatusSuccess(networkState: NetworkState) {
        _networkStateLiveData.postValue(ru.androidlearning.core.DataLoadingState.Success(networkState))
        currentNetworkState = networkState
    }

    private fun doOnGetNetworkStatusError(e: Throwable) {
        e.printStackTrace()
        _networkStateLiveData.postValue(ru.androidlearning.core.DataLoadingState.Error(e))
    }

    fun restoreViewState() {
        val dictionaryPresentationData = savedStateHandle.get<ru.androidlearning.core.DictionaryPresentationData>(SAVED_TRANSLATED_DATA_KEY)
        dictionaryPresentationData?.let {
            dataLoadingLiveData.postValue(ru.androidlearning.core.DataLoadingState.Success(it))
        }
    }

    override fun onCleared() {
        networkStateMonitor.stopMonitoring()
        super.onCleared()
    }
}
