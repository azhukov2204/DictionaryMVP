package ru.androidlearning.dictionary.ui.activity.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import ru.androidlearning.dictionary.data.network.NetworkState
import ru.androidlearning.dictionary.di.modules.AssistedSavedStateViewModelFactory
import ru.androidlearning.dictionary.schedulers.WorkSchedulers
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractor

private const val SAVED_TRANSLATED_DATA_KEY = "SavedTranslatedData"

class MainActivityViewModel @AssistedInject constructor(
    private val mainActivityInteractor: MainActivityInteractor,
    private val schedulers: WorkSchedulers,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MainActivityViewModel>

    private val _dataLoadingLiveData = MutableLiveData<DataLoadingState<DictionaryPresentationData>>()
    val dataLoadingLiveData: LiveData<DataLoadingState<DictionaryPresentationData>>
        get() = _dataLoadingLiveData

    private val _networkStateLiveData = MutableLiveData<DataLoadingState<NetworkState>>()
    val networkStateLiveData: LiveData<DataLoadingState<NetworkState>>
        get() = _networkStateLiveData

    private val disposables = CompositeDisposable()
    private var currentNetworkState: NetworkState = NetworkState.DISCONNECTED

    init {
        mainActivityInteractor.getNetworkStateObservable().let { networkStateConnectableObservable ->
            disposables +=
                networkStateConnectableObservable
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .subscribe(
                        ::doOnGetNetworkStatusSuccess,
                        ::doOnGetNetworkStatusError
                    )


            disposables +=
                networkStateConnectableObservable
                    .connect()
        }
    }

    fun translate(word: String, language: String) {
        if (word.isNotBlank() && currentNetworkState == NetworkState.CONNECTED) {
            disposables +=
                mainActivityInteractor.translate(word, language)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .doOnSubscribe { doOnTranslateSubscribe() }
                    .subscribe(
                        ::doOnTranslateSuccess,
                        ::doOnTranslateError
                    )
        }
    }

    private fun doOnTranslateSubscribe() {
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
        disposables.clear()
        super.onCleared()
    }
}
