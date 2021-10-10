package ru.androidlearning.presentation.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ru.androidlearning.core.DataLoadingState
import ru.androidlearning.core.base_abstract_templates.BaseMVVMViewModel
import ru.androidlearning.presentation.fragments.InstantSearchFlow
import ru.androidlearning.presentation.interactor.Interactor
import ru.androidlearning.utils.network.NetworkState
import ru.androidlearning.utils.network.NetworkStateMonitor

private const val SAVED_TRANSLATED_DATA_KEY = "SavedTranslatedData"
private const val DEBOUNCE_TIME_MS = 500L

@FlowPreview
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
        initInstantSearch()
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

    private fun initInstantSearch() {
        coroutineScopeIO.launch {
            InstantSearchFlow.searchFlow
                .debounce(DEBOUNCE_TIME_MS)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collectLatest(::search)
        }
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

    private fun doOnTranslateSuccess(dictionaryPresentationData: ru.androidlearning.core.DictionaryPresentationData) {
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
        val dictionaryPresentationData = savedStateHandle.get<ru.androidlearning.core.DictionaryPresentationData>(SAVED_TRANSLATED_DATA_KEY)
        dictionaryPresentationData?.let {
            dataLoadingLiveData.postValue(DataLoadingState.Success(it))
        }
    }

    override fun onCleared() {
        networkStateMonitor.stopMonitoring()
        super.onCleared()
    }
}
