package ru.androidlearning.dictionary.di.modules

import dagger.Binds
import dagger.Module
import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.data.repository.DictionaryRepositoryImpl
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSource
import ru.androidlearning.dictionary.data.repository.datasource.DictionaryDataSourceImpl

@Module
interface RepositoryModule {
    @Binds
    fun bindDictionaryRepository(dictionaryRepository: DictionaryRepositoryImpl): DictionaryRepository

    @Binds
    fun bindDictionaryDataSource(dictionaryDataSource: DictionaryDataSourceImpl): DictionaryDataSource
}
