package ru.androidlearning.dictionary.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.androidlearning.dictionary.application.EnRuDictionaryApp
import ru.androidlearning.dictionary.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        MainActivityInteractorModule::class,
        ApiModule::class,
        RepositoryModule::class,
        WorkSchedulersModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<EnRuDictionaryApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        fun build(): ApplicationComponent
    }
}
