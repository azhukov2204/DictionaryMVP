package ru.androidlearning.presentation.di.modules

import org.koin.dsl.module
import ru.androidlearning.utils.network.NetworkStateMonitor

val networkStateMonitorModule = module {
    single { NetworkStateMonitor(context = get()) }
}
