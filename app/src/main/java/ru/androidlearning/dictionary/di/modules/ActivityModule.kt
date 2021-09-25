package ru.androidlearning.dictionary.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.androidlearning.dictionary.ui.activity.MainActivity

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}