package ru.androidlearning.dictionary.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidlearning.dictionary.data.repository.datasource.api.DictionaryApi
import ru.androidlearning.dictionary.data.repository.datasource.api.TokenInterceptor
import ru.androidlearning.dictionary.di.DictionaryBaseUrl

@Module
class ApiModule {

    @DictionaryBaseUrl
    @Provides
    fun provideDictionaryBaseUrl(): String = "https://dictionary.yandex.net/api/v1/dicservice.json/"

    @Reusable
    @Provides
    fun provideDictionaryApi(@DictionaryBaseUrl baseUrl: String): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor)
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                    .build()
            )
            .build()
            .create(DictionaryApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .create()
}
