package ru.androidlearning.model.repository.datasource.local

import ru.androidlearning.model.SearchDto
import ru.androidlearning.model.repository.datasource.DictionaryDataSource

interface DictionaryDataSourceLocal : DictionaryDataSource {
    suspend fun retain(searchDto: SearchDto)
    suspend fun retain(searchDtoList: List<SearchDto>)
    suspend fun getHistory(): List<SearchDto>
}
