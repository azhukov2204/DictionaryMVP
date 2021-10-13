package ru.androidlearning.presentation.di.modules

import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidlearning.presentation.fragments.history.HistoryFragment
import ru.androidlearning.presentation.fragments.history.HistoryFragmentViewModel
import ru.androidlearning.presentation.fragments.search.SearchFragment
import ru.androidlearning.presentation.fragments.search.SearchFragmentViewModel
import ru.androidlearning.presentation.fragments.search.dialog.SearchHistoryDialogFragment
import ru.androidlearning.presentation.fragments.search.dialog.SearchHistoryDialogViewModel
import ru.androidlearning.presentation.fragments.stopwatch.StopwatchFragment
import ru.androidlearning.presentation.fragments.stopwatch.StopwatchViewModel

@FlowPreview
val viewModelsModule = module {
    scope(named<SearchFragment>()) {
        viewModel {
            SearchFragmentViewModel(
                interactor = get(),
                networkStateMonitor = get(),
                savedStateHandle = get()
            )
        }
    }

    scope(named<HistoryFragment>()) {
        viewModel {
            HistoryFragmentViewModel(
                interactor = get()
            )
        }
    }

    scope(named<SearchHistoryDialogFragment>()) {
        viewModel {
            SearchHistoryDialogViewModel(
                interactor = get()
            )
        }
    }

    scope(named<StopwatchFragment>()) {
        viewModel {
            StopwatchViewModel(interactor = get())
        }
    }
}
