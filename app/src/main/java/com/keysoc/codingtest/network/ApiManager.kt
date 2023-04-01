package com.keysoc.codingtest.network

import android.util.Log
import com.keysoc.codingtest.util.CONNECTION_TIMEOUT
import com.keysoc.codingtest.util.READ_TIMEOUT
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {
    inline fun <reified T> create(baseUrl: String, retrofitEventListener: EventListener? = null): T {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("ApiManager", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .apply {
                retrofitEventListener?.let {
                    eventListener(it)
                }
            }
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder)
            .build()

        return retrofit.create(T::class.java)
    }
}