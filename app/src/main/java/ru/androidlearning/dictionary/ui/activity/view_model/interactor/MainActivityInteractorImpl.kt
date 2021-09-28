package ru.androidlearning.dictionary.ui.activity.view_model.interactor

import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

class MainActivityInteractorImpl(
    private val dictionaryRepository: DictionaryRepository,
) : MainActivityInteractor {

    override suspend fun translate(word: String, language: String): DictionaryPresentationData =
        dictionaryRepository.translate(word, language)
            .let(DictionaryPresentationData.Mapper::map)
}
