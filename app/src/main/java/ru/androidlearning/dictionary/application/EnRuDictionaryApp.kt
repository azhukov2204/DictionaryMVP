package ru.androidlearning.dictionary.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.androidlearning.dictionary.di.diModules

class EnRuDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(diModules)
        }
    }
}
