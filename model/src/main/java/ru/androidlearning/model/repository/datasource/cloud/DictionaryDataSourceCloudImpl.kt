package ru.androidlearning.model.repository.datasource.cloud

import ru.androidlearning.model.SearchData
import ru.androidlearning.model.repository.datasource.cloud.api.DictionaryApi

class DictionaryDataSourceCloudImpl(private val dictionaryApi: DictionaryApi) : DictionaryDataSourceCloud {

    override suspend fun search(word: String): List<SearchData> =
        dictionaryApi.search(word).await()
}
