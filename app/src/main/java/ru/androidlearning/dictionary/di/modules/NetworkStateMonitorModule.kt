package ru.androidlearning.dictionary.di.modules

import org.koin.dsl.module
import ru.androidlearning.dictionary.utils.network.NetworkStateMonitor

internal val networkStateMonitorModule = module {
    single { NetworkStateMonitor(context = get()) }
}
