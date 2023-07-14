package com.mohsen.itollhub.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class ConnectionChecker @Inject constructor(private val connectivityManager: ConnectivityManager) {

    fun isConnectionOn(): Boolean {
        val network = connectivityManager.activeNetwork
        val connection = connectivityManager.getNetworkCapabilities(network)

        return connection != null && (connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || connection.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ) || connection.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
    }

}