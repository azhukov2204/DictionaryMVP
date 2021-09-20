package ru.androidlearning.dictionary.data.repository.datasource.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidlearning.dictionary.data.DictionaryData

interface DictionaryApi {
    @GET("lookup")
    fun translate(
        @Query("text") word: String,
        @Query("lang") lang: String
    ): Single<DictionaryData>
}
