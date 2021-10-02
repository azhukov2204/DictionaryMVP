package ru.androidlearning.dictionary.data.repository.datasource

import ru.androidlearning.dictionary.data.SearchData

interface DictionaryDataSource {
    suspend fun search(word: String): List<SearchData>
}
