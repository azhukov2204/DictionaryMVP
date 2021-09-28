package ru.androidlearning.dictionary.data.repository.datasource

import ru.androidlearning.dictionary.data.DictionaryData
import ru.androidlearning.dictionary.data.repository.datasource.api.DictionaryApi

class DictionaryDataSourceImpl(private val dictionaryApi: DictionaryApi) : DictionaryDataSource {

    override suspend fun translate(word: String, lang: String): DictionaryData =
        dictionaryApi.translate(word, lang).await()
}
