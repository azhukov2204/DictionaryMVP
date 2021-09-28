package ru.androidlearning.dictionary.di.modules

import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.fragments.search.view_model.interactor.SearchFragmentInteractor
import ru.androidlearning.dictionary.ui.fragments.search.view_model.interactor.SearchFragmentInteractorImpl

internal val mainActivityInteractorModule = module {
    factory<SearchFragmentInteractor> {
        SearchFragmentInteractorImpl(dictionaryRepository = get())
    }
}
