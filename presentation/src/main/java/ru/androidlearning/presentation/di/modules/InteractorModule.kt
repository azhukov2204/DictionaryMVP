package ru.androidlearning.presentation.di.modules

import org.koin.dsl.module
import ru.androidlearning.presentation.interactor.Interactor
import ru.androidlearning.presentation.interactor.InteractorImpl

val interactorModule = module {
    factory<Interactor> {
        InteractorImpl(
            dictionaryRepository = get(),
            stopwatchOrchestrators = arrayOf(get(), get())
        )
    }
}
