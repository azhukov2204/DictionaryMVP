package ru.androidlearning.model.repository.datasource.local.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.androidlearning.model.SearchData
import ru.androidlearning.model.repository.datasource.local.storage.dao.DictionaryDao

@Database(exportSchema = true, entities = [SearchData::class], version = 1)
@TypeConverters(StorageConverters::class)
abstract class DictionaryStorage : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}
