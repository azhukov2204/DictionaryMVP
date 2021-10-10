package ru.androidlearning.dictionary.di

import ru.androidlearning.dictionary.di.modules.ciceroneModule
import ru.androidlearning.model.di.modules.apiModule
import ru.androidlearning.model.di.modules.dataSourcesModule
import ru.androidlearning.model.di.modules.stopwatchModule
import ru.androidlearning.presentation.di.modules.*

internal val diModules = listOf(
    apiModule,
    networkStateMonitorModule,
    dataSourcesModule,
    repositoryModule,
    viewModelsModule,
    ciceroneModule,
    interactorModule,
    stopwatchModule,
    stopwatchListOrchestratorModule
)
