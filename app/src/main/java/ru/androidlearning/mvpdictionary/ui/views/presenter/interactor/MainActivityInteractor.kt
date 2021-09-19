package ru.androidlearning.mvpdictionary.ui.views.presenter.interactor

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.mvpdictionary.ui.DictionaryPresentationData

interface MainActivityInteractor {
    fun translate(word: String, language: String): Single<DictionaryPresentationData>
}
