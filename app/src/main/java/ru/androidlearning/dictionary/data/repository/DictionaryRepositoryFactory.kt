package ru.androidlearning.dictionary.data.repository

import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSourceFactory

object DictionaryRepositoryFactory {

    private val dictionaryRepository: DictionaryRepository by lazy { DictionaryRepositoryImpl(DictionaryDataSourceFactory.create()) }

    fun create(): DictionaryRepository = dictionaryRepository
}
