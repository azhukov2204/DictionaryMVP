package ru.androidlearning.dictionary.di.modules

import androidx.room.Room
import org.koin.dsl.module
import ru.androidlearning.dictionary.data.repository.DictionaryRepository
import ru.androidlearning.dictionary.data.repository.DictionaryRepositoryImpl
import ru.androidlearning.dictionary.data.repository.datasource.cloud.DictionaryDataSourceCloud
import ru.androidlearning.dictionary.data.repository.datasource.cloud.DictionaryDataSourceCloudImpl
import ru.androidlearning.dictionary.data.repository.datasource.local.DictionaryDataSourceLocal
import ru.androidlearning.dictionary.data.repository.datasource.local.DictionaryDataSourceLocalImpl
import ru.androidlearning.dictionary.data.repository.datasource.local.storage.DictionaryStorage

private const val DB_FILE_NAME = "dictionary.db"

internal val repositoryModule = module {
    factory<DictionaryRepository> { DictionaryRepositoryImpl(dictionaryDataSourceCloud = get(), dictionaryDataSourceLocal = get()) }
    factory<DictionaryDataSourceCloud> { DictionaryDataSourceCloudImpl(dictionaryApi = get()) }
    factory<DictionaryDataSourceLocal> { DictionaryDataSourceLocalImpl(dictionaryStorage = get()) }
    single {
        Room.databaseBuilder(get(), DictionaryStorage::class.java, DB_FILE_NAME)
            .build()
    }
}
