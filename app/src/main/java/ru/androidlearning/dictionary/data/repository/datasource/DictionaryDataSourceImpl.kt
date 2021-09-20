package ru.androidlearning.dictionary.data.repository.datasource

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.dictionary.data.DictionaryData
import ru.androidlearning.dictionary.data.repository.datasource.api.DictionaryApi
import javax.inject.Inject

class DictionaryDataSourceImpl @Inject constructor(private val dictionaryApi: DictionaryApi) : DictionaryDataSource {

    override fun translate(word: String, lang: String): Single<DictionaryData> =
        dictionaryApi.translate(word, lang)
}
