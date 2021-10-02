package ru.androidlearning.presentation.di.modules

import org.koin.dsl.module
import ru.androidlearning.model.repository.DictionaryRepository
import ru.androidlearning.model.repository.DictionaryRepositoryImpl

val repositoryModule = module {
    factory<DictionaryRepository> { DictionaryRepositoryImpl(dictionaryDataSourceCloud = get(), dictionaryDataSourceLocal = get()) }
}
