package ru.androidlearning.dictionary.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.androidlearning.dictionary.di.DaggerApplicationComponent

class EnRuDictionaryApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out EnRuDictionaryApp> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .build()
}
