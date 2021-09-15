package ru.androidlearning.mvpdictionary.data.repository

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.mvpdictionary.data.DictionaryData

interface DictionaryRepository {
    fun translate(word: String, lang: String): Single<DictionaryData>
}
