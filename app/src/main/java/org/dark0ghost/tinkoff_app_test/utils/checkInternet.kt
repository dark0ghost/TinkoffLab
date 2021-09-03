package org.dark0ghost.tinkoff_app_test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isNetworkAvailable(context: Context): Boolean {
    // api >= 28
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetworks = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        activeNetworks.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}