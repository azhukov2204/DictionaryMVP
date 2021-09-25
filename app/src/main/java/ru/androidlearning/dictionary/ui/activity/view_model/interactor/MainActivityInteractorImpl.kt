package ru.androidlearning.dictionary.ui.activity.view_model.interactor

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observables.ConnectableObservable
import ru.androidlearning.dictionary.data.network.NetworkState
import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.di.NetworkStateMonitor
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import javax.inject.Inject

class MainActivityInteractorImpl @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
    @NetworkStateMonitor private val networkStateConnectableObservable: ConnectableObservable<NetworkState>
) : MainActivityInteractor {

    override fun translate(word: String, language: String): Single<DictionaryPresentationData> =
        dictionaryRepository.translate(word, language)
            .map(DictionaryPresentationData.Mapper::map)

    override fun getNetworkStateObservable(): ConnectableObservable<NetworkState> =
        networkStateConnectableObservable
}
