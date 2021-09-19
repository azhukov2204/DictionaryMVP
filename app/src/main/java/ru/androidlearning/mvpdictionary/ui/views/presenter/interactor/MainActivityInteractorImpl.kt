package ru.androidlearning.mvpdictionary.ui.views.presenter.interactor

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.mvpdictionary.data.repository.DictionaryRepository
import ru.androidlearning.mvpdictionary.ui.DictionaryPresentationData

class MainActivityInteractorImpl(
    private val dictionaryRepository: DictionaryRepository
) : MainActivityInteractor {
    override fun translate(word: String, language: String): Single<DictionaryPresentationData> =
        dictionaryRepository.translate(word, language)
            .map(DictionaryPresentationData.Mapper::map)
}
