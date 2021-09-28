package ru.androidlearning.dictionary.data.repository.datasource.cloud.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidlearning.dictionary.data.SearchData

interface DictionaryApi {
    @GET("words/search")
    fun search(
        @Query("search") word: String
    ): Deferred<List<SearchData>>
}
