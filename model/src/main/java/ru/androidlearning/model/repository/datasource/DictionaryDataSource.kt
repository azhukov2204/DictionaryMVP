package ru.androidlearning.model.repository.datasource

import ru.androidlearning.model.SearchDto

interface DictionaryDataSource {
    suspend fun search(word: String): List<SearchDto>
}
