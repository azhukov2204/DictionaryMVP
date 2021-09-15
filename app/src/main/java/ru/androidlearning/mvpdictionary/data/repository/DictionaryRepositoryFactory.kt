package ru.androidlearning.mvpdictionary.data.repository

import ru.androidlearning.mvpdictionary.data.repository.datasource.DictionaryDataSourceFactory

object DictionaryRepositoryFactory {

    private val dictionaryRepository: DictionaryRepository by lazy { DictionaryRepositoryImpl(DictionaryDataSourceFactory.create()) }

    fun create(): DictionaryRepository = dictionaryRepository
}
