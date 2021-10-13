package ru.androidlearning.presentation.di.modules

import kotlinx.coroutines.FlowPreview
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidlearning.presentation.fragments.search.SearchFragment
import ru.androidlearning.utils.network.NetworkStateMonitor

@FlowPreview
val networkStateMonitorModule = module {
    scope(named<SearchFragment>()) {
        scoped { NetworkStateMonitor(context = get()) }
    }
}
