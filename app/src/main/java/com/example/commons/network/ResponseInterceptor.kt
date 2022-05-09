package com.example.commons.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException


class ResponseInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        try {
            val request: Request = chain.request()
            val response: Response = chain.proceed(request)
            val rawJson: String = response.body!!.string()
            Log.d(
                "DSErrorResponse",
                request.url.encodedPath + " , " + response.code.toString() + " , " + rawJson
            )

            when (response.code) {
                400 -> {
                    throw Exception("Invalid properties")
                }
                404 -> {
                    throw Exception("The coffee machine does not exist")
                }
            }

            return response.newBuilder()
                .code(response.code)
                .body(ResponseBody.create(response.body!!.contentType(), rawJson)).build()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return chain.proceed(chain.request())

    }
}
