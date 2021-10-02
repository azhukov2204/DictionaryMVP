package ru.androidlearning.dictionary.data.repository.datasource.cloud

import ru.androidlearning.dictionary.data.SearchData
import ru.androidlearning.dictionary.data.repository.datasource.cloud.api.DictionaryApi

class DictionaryDataSourceCloudImpl(private val dictionaryApi: DictionaryApi) : DictionaryDataSourceCloud {

    override suspend fun search(word: String): List<SearchData> =
        dictionaryApi.search(word).await()
}
