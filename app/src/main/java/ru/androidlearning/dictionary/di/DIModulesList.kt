package ru.androidlearning.dictionary.di

import ru.androidlearning.dictionary.di.modules.ciceroneModule
import ru.androidlearning.model.di.modules.apiModule
import ru.androidlearning.model.di.modules.dataSourcesModule
import ru.androidlearning.presentation.di.modules.interactorModule
import ru.androidlearning.presentation.di.modules.networkStateMonitorModule
import ru.androidlearning.presentation.di.modules.repositoryModule
import ru.androidlearning.presentation.di.modules.viewModelsModule

internal val diModules = listOf(
    apiModule,
    networkStateMonitorModule,
    dataSourcesModule,
    repositoryModule,
    viewModelsModule,
    ciceroneModule,
    interactorModule
)
