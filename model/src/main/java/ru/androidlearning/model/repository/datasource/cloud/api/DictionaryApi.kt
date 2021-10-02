package ru.androidlearning.model.repository.datasource.cloud.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidlearning.model.SearchData

interface DictionaryApi {
    @GET("words/search")
    fun search(
        @Query("search") word: String
    ): Deferred<List<SearchData>>
}
