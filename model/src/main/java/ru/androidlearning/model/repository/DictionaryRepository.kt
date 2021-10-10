package ru.androidlearning.model.repository

import ru.androidlearning.model.SearchDto

interface DictionaryRepository {
    suspend fun search(word: String, isOnline: Boolean): List<SearchDto>
    suspend fun getHistory(): List<SearchDto>
}
