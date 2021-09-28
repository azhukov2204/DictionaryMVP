package ru.androidlearning.dictionary.data.repository.datasource.local

import ru.androidlearning.dictionary.data.SearchData
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSource

interface DictionaryDataSourceLocal : DictionaryDataSource {
    suspend fun retain(searchData: SearchData)
    suspend fun retain(searchDataList: List<SearchData>)
    suspend fun getHistory(): List<SearchData>
}
