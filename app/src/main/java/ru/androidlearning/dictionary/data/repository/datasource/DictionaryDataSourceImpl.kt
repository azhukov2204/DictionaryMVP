package ru.androidlearning.dictionary.data.repository.datasource

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.data.DictionaryData
import ru.androidlearning.dictionary.data.repository.datasource.api.DictionaryApi

class DictionaryDataSourceImpl(private val dictionaryApi: DictionaryApi) : DictionaryDataSource {

    override fun translate(word: String, lang: String): Single<DictionaryData> =
        dictionaryApi.translate(word, lang)
}
