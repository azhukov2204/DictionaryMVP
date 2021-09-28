package ru.androidlearning.dictionary.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.activity.view_model.MainActivityViewModel

internal val viewModelModule = module {
    viewModel {
        MainActivityViewModel(
            mainActivityInteractor = get(),
            networkStateMonitor = get(),
            savedStateHandle = get()
        )
    }
}
