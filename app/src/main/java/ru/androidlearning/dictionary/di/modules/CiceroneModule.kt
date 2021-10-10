package ru.androidlearning.dictionary.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidlearning.dictionary.ui.activity.MainActivity

internal val ciceroneModule = module {
    val cicerone: Cicerone<Router> = Cicerone.create()
    single { cicerone.router }
    scope(named<MainActivity>()) {
        scoped { cicerone.getNavigatorHolder() }
    }
}
