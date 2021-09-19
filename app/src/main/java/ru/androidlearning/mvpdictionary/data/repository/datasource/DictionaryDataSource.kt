package ru.androidlearning.mvpdictionary.data.repository.datasource

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.mvpdictionary.data.DictionaryData

interface DictionaryDataSource {
    fun translate(word: String, lang: String): Single<DictionaryData>
}
