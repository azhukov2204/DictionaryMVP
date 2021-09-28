package ru.androidlearning.dictionary.ui.fragments.search

import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.dictionary.ui.interactor.Interactor
import ru.androidlearning.dictionary.utils.network.NetworkState
import ru.androidlearning.dictionary.utils.network.NetworkStateMonitor

private const val SAVED_TRANSLATED_DATA_KEY = "SavedTranslatedData"

class SearchFragmentViewModel(
    private val interactor: Interactor,
    private val networkStateMonitor: NetworkStateMonitor,
    private val savedStateHandle: SavedStateHandle,
) : BaseMVVMViewModel() {

    private val _networkStateLiveData = MutableLiveData<DataLoadingState<NetworkState>>()
    val networkStateLiveData: LiveData<DataLoadingState<NetworkState>>
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
        dataLoadingLiveData.postValue(DataLoadingState.Loading())
    }

    private fun doOnTranslateSuccess(dictionaryPresentationData: DictionaryPresentationData) {
        dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationData))
        savedStateHandle[SAVED_TRANSLATED_DATA_KEY] = dictionaryPresentationData
    }

    private fun doOnTranslateError(e: Throwable) {
        e.printStackTrace()
        dataLoadingLiveData.postValue(DataLoadingState.Error(e))
    }

    private fun doOnGetNetworkStatusSuccess(networkState: NetworkState) {
        _networkStateLiveData.postValue(DataLoadingState.Success(networkState))
        currentNetworkState = networkState
    }

    private fun doOnGetNetworkStatusError(e: Throwable) {
        e.printStackTrace()
        _networkStateLiveData.postValue(DataLoadingState.Error(e))
    }

    fun restoreViewState() {
        val dictionaryPresentationData = savedStateHandle.get<DictionaryPresentationData>(SAVED_TRANSLATED_DATA_KEY)
        dictionaryPresentationData?.let {
            dataLoadingLiveData.postValue(DataLoadingState.Success(it))
        }
    }

    override fun onCleared() {
        networkStateMonitor.stopMonitoring()
        super.onCleared()
    }
}
