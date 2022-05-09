package com.example.commons.network

import android.app.Activity
import com.example.commons.ui.NoInternetPopup
import com.example.commons.utilities.CheckInternetConnection
import com.example.commons.utilities.Constant
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(var activity: Activity) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!CheckInternetConnection.isOnline(activity.applicationContext)) {
            showNoInternetPopup()

            throw Exception()
        } else {
            chain.proceed(chain.request())
        }

    }

    private fun showNoInternetPopup() {
        val noInternetPopup = NoInternetPopup()
        if (Constant.isNoInternetPopShown) {
            noInternetPopup.showNoInternetPopup(activity)
        }

    }

}