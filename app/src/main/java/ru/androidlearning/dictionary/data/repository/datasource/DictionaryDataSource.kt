package ru.androidlearning.dictionary.data.repository.datasource

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.data.DictionaryData

interface DictionaryDataSource {
    fun translate(word: String, lang: String): Single<DictionaryData>
}
