package com.therealdroid.github.network.implementation.login

import com.google.gson.GsonBuilder
import com.therealdroid.github.network.config.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitManager {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //This will make retrofit use Rx Calls instead Retrofit Calls
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) // Delegate to Gson to make the conversions
        .client(OkHttpClient())
        .build()

    fun <T> provideService(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}
