package ru.androidlearning.dictionary.di.modules

import org.koin.dsl.module
import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.data.repository.DictionaryRepositoryImpl
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSource
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSourceImpl

internal val repositoryModule = module {
    factory<DictionaryRepository> { DictionaryRepositoryImpl(dictionaryDataSource = get()) }
    factory<DictionaryDataSource> { DictionaryDataSourceImpl(dictionaryApi = get()) }
}
