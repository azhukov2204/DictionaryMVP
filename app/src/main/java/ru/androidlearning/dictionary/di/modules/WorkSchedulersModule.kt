package ru.androidlearning.dictionary.di.modules

import dagger.Module
import dagger.Provides
import ru.androidlearning.dictionary.schedulers.WorkSchedulers
import ru.androidlearning.dictionary.schedulers.WorkSchedulersImpl

@Module
class WorkSchedulersModule {

    @Provides
    fun provideWorkSchedulers(): WorkSchedulers = WorkSchedulersImpl()
}
