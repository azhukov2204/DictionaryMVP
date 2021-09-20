package ru.androidlearning.dictionary.di.modules

import dagger.Binds
import dagger.Module
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractor
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractorImpl

@Module
interface MainActivityInteractorModule {

    @Binds
    fun bindMainActivityInteractor(mainActivityInteractor: MainActivityInteractorImpl): MainActivityInteractor
}