package ru.androidlearning.dictionary.data.repository

import ru.androidlearning.dictionary.data.DictionaryData

interface DictionaryRepository {
    suspend fun translate(word: String, lang: String): DictionaryData
}
