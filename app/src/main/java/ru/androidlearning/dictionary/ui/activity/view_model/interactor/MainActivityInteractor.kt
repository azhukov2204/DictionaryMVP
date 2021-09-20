package ru.androidlearning.dictionary.ui.activity.view_model.interactor

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

interface MainActivityInteractor {
    fun translate(word: String, language: String): Single<DictionaryPresentationData>
}
