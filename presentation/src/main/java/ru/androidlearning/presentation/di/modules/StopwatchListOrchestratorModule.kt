package ru.androidlearning.presentation.di.modules

import org.koin.dsl.module
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator
import ru.androidlearning.model.stopwatch.StopwatchOrchestratorImpl

val stopwatchListOrchestratorModule = module {
    factory<StopwatchOrchestrator> { StopwatchOrchestratorImpl(stopwatchStateHolder = get()) }
}
