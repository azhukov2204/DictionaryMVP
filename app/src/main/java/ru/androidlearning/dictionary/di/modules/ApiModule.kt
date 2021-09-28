package ru.androidlearning.dictionary.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidlearning.dictionary.BuildConfig
import ru.androidlearning.dictionary.data.repository.datasource.api.DictionaryApi
import ru.androidlearning.dictionary.data.repository.datasource.api.TokenInterceptor

private const val DICTIONARY_BASE_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/"

private val gson: Gson =
    GsonBuilder()
        .create()

internal val apiModule = module {
    single<DictionaryApi> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                }
            }
            .build()

        Retrofit.Builder()
            .baseUrl(DICTIONARY_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(DictionaryApi::class.java)
    }
}
