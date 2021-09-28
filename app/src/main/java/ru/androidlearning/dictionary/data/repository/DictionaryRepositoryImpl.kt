package ru.androidlearning.dictionary.data.repository

import ru.androidlearning.dictionary.data.DictionaryData
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSource

class DictionaryRepositoryImpl(private val dictionaryDataSource: DictionaryDataSource) : DictionaryRepository {
    override suspend fun translate(word: String, lang: String): DictionaryData =
        dictionaryDataSource.translate(word, lang)
}
