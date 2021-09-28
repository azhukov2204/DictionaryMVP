package ru.androidlearning.dictionary.data.repository

import ru.androidlearning.dictionary.data.SearchData
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSource

class DictionaryRepositoryImpl(private val dictionaryDataSource: DictionaryDataSource) : DictionaryRepository {
    override suspend fun search(word: String): List<SearchData> =
        dictionaryDataSource.search(word)
}
