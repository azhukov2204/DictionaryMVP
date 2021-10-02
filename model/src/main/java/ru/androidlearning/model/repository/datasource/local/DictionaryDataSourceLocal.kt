package ru.androidlearning.model.repository.datasource.local

import ru.androidlearning.model.SearchData
import ru.androidlearning.model.repository.datasource.DictionaryDataSource

interface DictionaryDataSourceLocal : DictionaryDataSource {
    suspend fun retain(searchData: SearchData)
    suspend fun retain(searchDataList: List<SearchData>)
    suspend fun getHistory(): List<SearchData>
}
