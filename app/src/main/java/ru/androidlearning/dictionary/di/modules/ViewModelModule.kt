package ru.androidlearning.dictionary.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.fragments.search.view_model.SearchFragmentViewModel

internal val viewModelModule = module {
    viewModel {
        SearchFragmentViewModel(
            searchFragmentInteractor = get(),
            networkStateMonitor = get(),
            savedStateHandle = get()
        )
    }
}
