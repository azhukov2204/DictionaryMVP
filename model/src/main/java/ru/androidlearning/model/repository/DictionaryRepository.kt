package ru.androidlearning.model.repository

import ru.androidlearning.model.SearchData

interface DictionaryRepository {
    suspend fun search(word: String, isOnline: Boolean): List<SearchData>
    suspend fun getHistory(): List<SearchData>
}
