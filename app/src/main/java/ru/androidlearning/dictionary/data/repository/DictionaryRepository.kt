package ru.androidlearning.dictionary.data.repository

import ru.androidlearning.dictionary.data.SearchData

interface DictionaryRepository {
    suspend fun search(word: String, isOnline: Boolean): List<SearchData>
    suspend fun getHistory(): List<SearchData>
}
