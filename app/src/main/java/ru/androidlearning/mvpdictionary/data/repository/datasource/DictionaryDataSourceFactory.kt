package ru.androidlearning.mvpdictionary.data.repository.datasource

import ru.androidlearning.mvpdictionary.data.repository.datasource.api.DictionaryApiFactory

object DictionaryDataSourceFactory {

    private val dictionaryDataSource: DictionaryDataSource by lazy { DictionaryDataSourceImpl(DictionaryApiFactory.create()) }

    fun create(): DictionaryDataSource = dictionaryDataSource
}
