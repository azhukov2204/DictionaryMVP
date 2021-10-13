package ru.androidlearning.model.repository.datasource.local.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.androidlearning.model.SearchDto

@Dao
interface DictionaryDao {
    @Insert(onConflict = REPLACE)
    suspend fun retain(searchDto: SearchDto)

    @Insert(onConflict = REPLACE)
    suspend fun retain(searchDtoList: List<SearchDto>)

    @Query("SELECT * FROM search_data")
    suspend fun getAllWords(): List<SearchDto>

    @Query("SELECT * FROM search_data WHERE lower(word) like '%' || lower(:word) || '%'")
    suspend fun getWords(word: String): List<SearchDto>
}
