package ru.androidlearning.dictionary.ui.interactor

import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

class InteractorImpl(
    private val dictionaryRepository: DictionaryRepository,
) : Interactor {

    override suspend fun getHistory(): DictionaryPresentationData =
        dictionaryRepository
            .getHistory()
            .let(DictionaryPresentationData.Mapper::map)

    override suspend fun search(word: String, isOnline: Boolean): DictionaryPresentationData =
        dictionaryRepository.search(word, isOnline)
            .let(DictionaryPresentationData.Mapper::map)
}
