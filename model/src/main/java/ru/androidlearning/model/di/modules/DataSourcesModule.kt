package ru.androidlearning.model.di.modules

import androidx.room.Room
import org.koin.dsl.module
import ru.androidlearning.model.repository.datasource.cloud.DictionaryDataSourceCloud
import ru.androidlearning.model.repository.datasource.cloud.DictionaryDataSourceCloudImpl
import ru.androidlearning.model.repository.datasource.local.DictionaryDataSourceLocal
import ru.androidlearning.model.repository.datasource.local.DictionaryDataSourceLocalImpl
import ru.androidlearning.model.repository.datasource.local.storage.DictionaryStorage

private const val DB_FILE_NAME = "dictionary.db"

val dataSourcesModule = module {
    factory<DictionaryDataSourceCloud> { DictionaryDataSourceCloudImpl(dictionaryApi = get()) }
    factory<DictionaryDataSourceLocal> { DictionaryDataSourceLocalImpl(dictionaryStorage = get()) }
    single {
        Room.databaseBuilder(get(), DictionaryStorage::class.java, DB_FILE_NAME)
            .build()
    }
}
