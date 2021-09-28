package ru.androidlearning.dictionary.di.modules

import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractor
import ru.androidlearning.dictionary.ui.activity.view_model.interactor.MainActivityInteractorImpl

internal val mainActivityInteractorModule = module {
    factory<MainActivityInteractor> {
        MainActivityInteractorImpl(dictionaryRepository = get())
    }
}
