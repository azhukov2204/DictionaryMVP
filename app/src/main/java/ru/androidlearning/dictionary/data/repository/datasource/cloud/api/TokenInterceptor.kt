package ru.androidlearning.dictionary.data.repository.datasource.cloud.api

import okhttp3.Interceptor
import okhttp3.Response
import ru.androidlearning.dictionary.BuildConfig

object TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().let { request ->
            request
                .url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.DICTIONARY_API_KEY)
                .build()
                .let { httpUrl ->
                    return chain.proceed(
                        request
                            .newBuilder()
                            .url(httpUrl)
                            .build()
                    )
                }
        }
    }
}
