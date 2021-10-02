package ru.androidlearning.presentation.interactor

import ru.androidlearning.model.repository.DictionaryRepository

class InteractorImpl(
    private val dictionaryRepository: DictionaryRepository,
) : Interactor {

    override suspend fun getHistory(): ru.androidlearning.core.DictionaryPresentationData =
        dictionaryRepository
            .getHistory()
            .let(ru.androidlearning.core.DictionaryPresentationData.Mapper::map)

    override suspend fun search(word: String, isOnline: Boolean): ru.androidlearning.core.DictionaryPresentationData =
        dictionaryRepository.search(word, isOnline)
            .let(ru.androidlearning.core.DictionaryPresentationData.Mapper::map)
}
