package com.therealdroid.github.network.implementation.login.routes


import com.therealdroid.github.network.implementation.login.models.ApiResponse
import com.therealdroid.github.network.implementation.login.models.ApiUser
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthRoute {

    @POST("/login")
    fun login(@Body apiCredentials: HashMap<String, String>): Single<ApiResponse>

    @POST("/register")
    fun register(@Body user: ApiUser): Single<ApiResponse>
}