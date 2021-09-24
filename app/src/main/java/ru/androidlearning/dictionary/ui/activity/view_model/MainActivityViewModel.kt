package ru.androidlearning.dictionary.ui.activity.view_model

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.androidlearning.dictionary.utils.network.NetworkState
import ru.androidlearning.dictionary.utils.network.NetworkStateMonitor
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractor

private const val SAVED_TRANSLATED_DATA_KEY = "SavedTranslatedData"

class MainActivityViewModel(
    private val mainActivityInteractor: MainActivityInteractor,
    private val networkStateMonitor: NetworkStateMonitor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _dataLoadingLiveData = MutableLiveData<DataLoadingState<DictionaryPresentationData>>()
    val dataLoadingLiveData: LiveData<DataLoadingState<DictionaryPresentationData>>
        get() = _dataLoadingLiveData

    private val _networkStateLiveData = MutableLiveData<DataLoadingState<NetworkState>>()
    val networkStateLiveData: LiveData<DataLoadingState<NetworkState>>
        get() = _networkStateLiveData

    private var currentNetworkState: NetworkState = NetworkState.DISCONNECTED

    init {
        startNetworkStateMonitoring()
    }

    private fun startNetworkStateMonitoring() {
        viewModelScope.launch(
            Dispatchers.IO
                    + CoroutineExceptionHandler { _, throwable -> doOnGetNetworkStatusError(throwable) }
        ) {
            for (state in networkStateMonitor.channel) {
                doOnGetNetworkStatusSuccess(state)
            }
        }
        networkStateMonitor.startMonitoring()
    }

    fun translate(word: String, language: String) {
        if (word.isNotBlank() && currentNetworkState == NetworkState.CONNECTED) {
            doBeforeTranslate()
            viewModelScope.launch(
                Dispatchers.IO
                        + CoroutineExceptionHandler { _, throwable -> doOnTranslateError(throwable) }
            ) {
                doOnTranslateSuccess(mainActivityInteractor.translate(word, language))
            }
        }
    }

    private fun doBeforeTranslate() {
        _dataLoadingLiveData.postValue(DataLoadingState.Loading())
    }

    private fun doOnTranslateSuccess(dictionaryPresentationData: DictionaryPresentationData) {
        _dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationData))
        savedStateHandle[SAVED_TRANSLATED_DATA_KEY] = dictionaryPresentationData
    }

    private fun doOnTranslateError(e: Throwable) {
        e.printStackTrace()
        _dataLoadingLiveData.postValue(DataLoadingState.Error(e))
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
            _dataLoadingLiveData.postValue(DataLoadingState.Success(it))
        }
    }

    override fun onCleared() {
        networkStateMonitor.stopMonitoring()
        super.onCleared()
    }
}
