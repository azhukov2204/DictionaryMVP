package ru.androidlearning.dictionary.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.fragments.history.HistoryFragmentViewModel
import ru.androidlearning.dictionary.ui.fragments.search.SearchFragmentViewModel
import ru.androidlearning.dictionary.ui.fragments.search.dialog.SearchHistoryDialogViewModel

internal val viewModelsModule = module {
    viewModel {
        SearchFragmentViewModel(
            interactor = get(),
            networkStateMonitor = get(),
            savedStateHandle = get()
        )
    }

    viewModel {
        HistoryFragmentViewModel(
            interactor = get()
        )
    }

    viewModel {
        SearchHistoryDialogViewModel(
            interactor = get()
        )
    }
}
