package ru.androidlearning.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class NetworkStateMonitor(context: Context) {
    val channel = Channel<NetworkState>()

    private val networkStateCoroutineScope = CoroutineScope(Dispatchers.IO)
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest: NetworkRequest by lazy {
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
            .build()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            networkStateCoroutineScope.launch {
                channel.send(NetworkState.CONNECTED)
            }
        }

        override fun onUnavailable() {
            networkStateCoroutineScope.launch {
                channel.send(NetworkState.DISCONNECTED)
            }
        }

        override fun onLost(network: Network) {
            networkStateCoroutineScope.launch {
                channel.send(NetworkState.DISCONNECTED)
            }
        }
    }

    @Suppress("DEPRECATION")
    fun startMonitoring() {
        val isConnected = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
        networkStateCoroutineScope.launch {
            channel.send(
                if (isConnected) {
                    NetworkState.CONNECTED
                } else {
                    NetworkState.DISCONNECTED
                }
            )
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun stopMonitoring() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
        channel.close()
        networkStateCoroutineScope.cancel()
    }
}
