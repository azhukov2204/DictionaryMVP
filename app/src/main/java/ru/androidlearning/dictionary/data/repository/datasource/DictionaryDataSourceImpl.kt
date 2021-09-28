package ru.androidlearning.dictionary.data.repository.datasource

import ru.androidlearning.dictionary.data.SearchData
import ru.androidlearning.dictionary.data.repository.datasource.api.DictionaryApi

class DictionaryDataSourceImpl(private val dictionaryApi: DictionaryApi) : DictionaryDataSource {

    override suspend fun search(word: String): List<SearchData> =
        dictionaryApi.search(word).await()
}
