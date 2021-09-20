package ru.androidlearning.dictionary.data.repository

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.data.DictionaryData

interface DictionaryRepository {
    fun translate(word: String, lang: String): Single<DictionaryData>
}
