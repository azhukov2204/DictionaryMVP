package ru.androidlearning.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkStateMonitor(context: Context) {
    private val _networkStateFlow = MutableStateFlow(NetworkState.DISCONNECTED)
    val networkStateFlow: Flow<NetworkState> = _networkStateFlow

    private val availableNetworks = mutableSetOf<Network>()
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder()
            .build()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            availableNetworks.add(network)
            if (availableNetworks.isNotEmpty()) {
                _networkStateFlow.value = NetworkState.CONNECTED
            }
        }

        override fun onLost(network: Network) {
            availableNetworks.remove(network)
            if (availableNetworks.isEmpty()) {
                _networkStateFlow.value = NetworkState.DISCONNECTED
            }
        }
    }

    @Suppress("DEPRECATION")
    fun startMonitoring() {
        val isConnected = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
        _networkStateFlow.value =
            if (isConnected) {
                NetworkState.CONNECTED
            } else {
                NetworkState.DISCONNECTED
            }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun stopMonitoring() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
