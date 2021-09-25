package ru.androidlearning.dictionary.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.observables.ConnectableObservable
import ru.androidlearning.dictionary.data.network.NetworkState
import ru.androidlearning.dictionary.data.network.NetworkStateObservable
import ru.androidlearning.dictionary.di.NetworkStateMonitor
import javax.inject.Singleton

@Module
class NetworkStateObservableModule {
    @Singleton
    @Provides
    @NetworkStateMonitor
    fun provideNetworkStateObservable(context: Context): ConnectableObservable<NetworkState> = NetworkStateObservable(context).publish()
}
