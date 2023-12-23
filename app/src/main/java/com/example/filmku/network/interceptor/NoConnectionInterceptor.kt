package com.example.filmku.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.example.filmku.util.NoConnectivityException
import com.example.filmku.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException

class NoConnectionInterceptor(context: Context) : Interceptor {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionOn()) {
            throw NoConnectivityException()
        } else if (!isInternetAvailable()) {
            throw NoInternetException()
        } else {
            chain.proceed(chain.request())
        }
    }

    @Suppress("DEPRECATION")
    private fun isConnectionOn(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                    else -> false
                }
            }
            return false
        } else {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork?.isConnected == true
        }
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockAddress = InetSocketAddress("8.8.8.8", 53)

            sock.connect(sockAddress, timeoutMs)
            sock.close()

            true
        } catch (e: SocketTimeoutException) {
            true // handle socket timeout pada class RequestExtension
        } catch (e: IOException) {
            false
        }
    }
}
