package ru.androidlearning.mvpdictionary.data.repository

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.mvpdictionary.data.DictionaryData
import ru.androidlearning.mvpdictionary.data.repository.datasource.DictionaryDataSource

class DictionaryRepositoryImpl(private val dictionaryDataSource: DictionaryDataSource) : DictionaryRepository {
    override fun translate(word: String, lang: String): Single<DictionaryData> =
        dictionaryDataSource.translate(word, lang)
}
