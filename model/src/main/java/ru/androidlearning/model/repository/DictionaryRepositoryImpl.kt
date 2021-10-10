package ru.androidlearning.model.repository

import ru.androidlearning.model.SearchDto
import ru.androidlearning.model.repository.datasource.cloud.DictionaryDataSourceCloud
import ru.androidlearning.model.repository.datasource.local.DictionaryDataSourceLocal

class DictionaryRepositoryImpl(
    private val dictionaryDataSourceCloud: DictionaryDataSourceCloud,
    private val dictionaryDataSourceLocal: DictionaryDataSourceLocal
) : DictionaryRepository {
    override suspend fun search(word: String, isOnline: Boolean): List<SearchDto> =
        if (isOnline) {
            dictionaryDataSourceCloud.search(word)
                .also { searchDataList ->
                    dictionaryDataSourceLocal.retain(searchDataList)
                }
        } else {
            dictionaryDataSourceLocal.search(word)
        }

    override suspend fun getHistory(): List<SearchDto> =
        dictionaryDataSourceLocal.getHistory()
}
