package ru.androidlearning.dictionary.data.repository.datasource

import ru.androidlearning.dictionary.data.DictionaryData

interface DictionaryDataSource {
    suspend fun translate(word: String, lang: String): DictionaryData
}
