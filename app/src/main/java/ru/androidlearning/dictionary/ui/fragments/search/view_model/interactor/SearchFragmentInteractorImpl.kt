package ru.androidlearning.dictionary.ui.fragments.search.view_model.interactor

import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

class SearchFragmentInteractorImpl(
    private val dictionaryRepository: DictionaryRepository,
) : SearchFragmentInteractor {

    override suspend fun search(word: String): DictionaryPresentationData =
        dictionaryRepository.search(word)
            .let(DictionaryPresentationData.Mapper::map)
}
