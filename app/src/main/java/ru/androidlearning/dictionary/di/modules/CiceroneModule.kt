package ru.androidlearning.dictionary.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

internal val ciceroneModule = module {
    val cicerone: Cicerone<Router> = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}
