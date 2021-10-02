package ru.androidlearning.dictionary.data.repository.datasource.local.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.androidlearning.dictionary.data.SearchData

@Dao
interface DictionaryDao {
    @Insert(onConflict = REPLACE)
    suspend fun retain(searchData: SearchData)

    @Insert(onConflict = REPLACE)
    suspend fun retain(searchDataList: List<SearchData>)

    @Query("SELECT * FROM search_data")
    suspend fun getAllWords(): List<SearchData>

    @Query("SELECT * FROM search_data WHERE lower(word) like '%' || lower(:word) || '%'")
    suspend fun getWords(word: String): List<SearchData>
}
