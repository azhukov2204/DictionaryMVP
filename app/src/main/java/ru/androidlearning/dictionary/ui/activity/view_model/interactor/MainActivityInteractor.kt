package ru.androidlearning.dictionary.ui.activity.view_model.interactor

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observables.ConnectableObservable
import ru.androidlearning.dictionary.data.network.NetworkState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

interface MainActivityInteractor {
    fun translate(word: String, language: String): Single<DictionaryPresentationData>
    fun getNetworkStateObservable(): ConnectableObservable<NetworkState>
}
