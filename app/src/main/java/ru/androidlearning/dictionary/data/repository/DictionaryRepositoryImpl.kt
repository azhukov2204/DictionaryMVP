package ru.androidlearning.dictionary.data.repository

import ru.androidlearning.dictionary.data.SearchData
import ru.androidlearning.dictionary.data.repository.datasource.cloud.DictionaryDataSourceCloud
import ru.androidlearning.dictionary.data.repository.datasource.local.DictionaryDataSourceLocal

class DictionaryRepositoryImpl(
    private val dictionaryDataSourceCloud: DictionaryDataSourceCloud,
    private val dictionaryDataSourceLocal: DictionaryDataSourceLocal
) : DictionaryRepository {
    override suspend fun search(word: String, isOnline: Boolean): List<SearchData> =
        if (isOnline) {
            dictionaryDataSourceCloud.search(word)
                .also { searchDataList ->
                    dictionaryDataSourceLocal.retain(searchDataList)
                }
        } else {
            dictionaryDataSourceLocal.search(word)
        }

    override suspend fun getHistory(): List<SearchData> =
        dictionaryDataSourceLocal.getHistory()
}
