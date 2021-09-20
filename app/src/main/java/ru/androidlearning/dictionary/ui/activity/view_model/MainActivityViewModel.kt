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
import ru.androidlearning.dictionary.di.modules.AssistedSavedStateViewModelFactory
import ru.androidlearning.dictionary.schedulers.WorkSchedulers
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractor

private const val SAVED_STATE_KEY = "SavedStateKey"

class MainActivityViewModel @AssistedInject constructor(
    private val mainActivityInteractor: MainActivityInteractor,
    private val schedulers: WorkSchedulers,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<MainActivityViewModel>

    private val _dataLoadingLiveData = MutableLiveData<DataLoadingState<DictionaryPresentationData>>()
    val dataLoadingLiveData: LiveData<DataLoadingState<DictionaryPresentationData>>
        get() = _dataLoadingLiveData

    private val disposables = CompositeDisposable()

    fun translate(word: String, language: String) {
        if (word.isNotBlank()) {
            disposables +=
                mainActivityInteractor.translate(word, language)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .doOnSubscribe { doOnSubscribe() }
                    .subscribe(
                        ::doOnSuccess,
                        ::doOnError
                    )
        }
    }

    private fun doOnSubscribe() {
        _dataLoadingLiveData.postValue(DataLoadingState.Loading())
    }

    private fun doOnSuccess(dictionaryPresentationData: DictionaryPresentationData) {
        _dataLoadingLiveData.postValue(DataLoadingState.Success(dictionaryPresentationData))
        savedStateHandle[SAVED_STATE_KEY] = dictionaryPresentationData
    }

    private fun doOnError(e: Throwable) {
        e.printStackTrace()
        _dataLoadingLiveData.postValue(DataLoadingState.Error(e))
    }

    fun restoreViewState() {
        val dictionaryPresentationData = savedStateHandle.get<DictionaryPresentationData>(SAVED_STATE_KEY)
        dictionaryPresentationData?.let {
            _dataLoadingLiveData.postValue(DataLoadingState.Success(it))
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
