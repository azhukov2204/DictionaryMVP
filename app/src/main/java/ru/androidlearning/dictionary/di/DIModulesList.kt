package ru.androidlearning.dictionary.di

import ru.androidlearning.dictionary.di.modules.*

internal val diModules = listOf(
    apiModule,
    networkStateMonitorModule,
    repositoryModule,
    viewModelsModule,
    ciceroneModule,
    interactorModule
)
