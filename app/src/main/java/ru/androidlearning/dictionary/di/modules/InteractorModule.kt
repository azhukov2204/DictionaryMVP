package ru.androidlearning.dictionary.di.modules

import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.interactor.Interactor
import ru.androidlearning.dictionary.ui.interactor.InteractorImpl

internal val interactorModule = module {
    factory<Interactor> {
        InteractorImpl(dictionaryRepository = get())
    }
}
