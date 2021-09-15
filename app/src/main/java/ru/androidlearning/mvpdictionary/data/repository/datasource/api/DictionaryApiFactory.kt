package ru.androidlearning.mvpdictionary.data.repository.datasource.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val DICTIONARY_BASE_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/"

object DictionaryApiFactory {

    private val gson: Gson =
        GsonBuilder()
            .create()

    fun create(): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(DICTIONARY_BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor)
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    .build()
            )
            .build()
            .create(DictionaryApi::class.java)
}
