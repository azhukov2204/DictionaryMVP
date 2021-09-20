package ru.androidlearning.dictionary.ui.activity.view_model.interactor

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import javax.inject.Inject

class MainActivityInteractorImpl @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : MainActivityInteractor {
    override fun translate(word: String, language: String): Single<DictionaryPresentationData> =
        dictionaryRepository.translate(word, language)
            .map(DictionaryPresentationData.Mapper::map)
}
