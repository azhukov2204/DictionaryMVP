package ru.androidlearning.dictionary.data.repository

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.data.DictionaryData
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSource
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(private val dictionaryDataSource: DictionaryDataSource) : DictionaryRepository {
    override fun translate(word: String, lang: String): Single<DictionaryData> =
        dictionaryDataSource.translate(word, lang)
}
