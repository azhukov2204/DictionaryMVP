package ru.androidlearning.model.repository.datasource

import ru.androidlearning.model.SearchData

interface DictionaryDataSource {
    suspend fun search(word: String): List<SearchData>
}
