package com.example.commons.network

import com.example.commons.pojo.CoffeeItResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface RetrofitService {


    @GET
    fun getCoffeeItApi(
        @Url url: String
    ): Single<CoffeeItResponse>

    companion object {

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): RetrofitService {

            return Retrofit.Builder()
                .client(getHttpClient(networkConnectionInterceptor))
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient(networkConnectionInterceptor))
                .build()
                .create(RetrofitService::class.java)
        }

        private fun getHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
            return builder.build()
        }
    }


}