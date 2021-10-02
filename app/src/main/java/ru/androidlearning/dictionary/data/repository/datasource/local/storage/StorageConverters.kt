package ru.androidlearning.dictionary.data.repository.datasource.local.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.androidlearning.dictionary.data.Meanings

class StorageConverters {
    @TypeConverter
    fun fromMeaningsToJson(value: List<Meanings>): String =
        Gson().toJson(value)

    @TypeConverter
    fun fromJsonToMeanings(value: String): List<Meanings> {
        val listType = object : TypeToken<List<Meanings>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
