package ru.androidlearning.presentation.di.modules

import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.androidlearning.presentation.fragments.history.HistoryFragmentViewModel
import ru.androidlearning.presentation.fragments.search.SearchFragmentViewModel
import ru.androidlearning.presentation.fragments.search.dialog.SearchHistoryDialogViewModel
import ru.androidlearning.presentation.fragments.stopwatch.StopwatchViewModel

@FlowPreview
val viewModelsModule = module {
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

    viewModel {
        StopwatchViewModel(interactor = get())
    }
}
