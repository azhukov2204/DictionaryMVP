package ru.androidlearning.model.repository.datasource.cloud

import ru.androidlearning.model.SearchDto
import ru.androidlearning.model.repository.datasource.cloud.api.DictionaryApi

class DictionaryDataSourceCloudImpl(private val dictionaryApi: DictionaryApi) : DictionaryDataSourceCloud {

    override suspend fun search(word: String): List<SearchDto> =
        dictionaryApi.search(word).await()
}
