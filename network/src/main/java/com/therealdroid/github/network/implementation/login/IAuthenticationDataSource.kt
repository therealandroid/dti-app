package com.therealdroid.github.network.implementation.login

import com.therealdroid.github.network.implementation.login.models.ApiResponse
import com.therealdroid.github.network.implementation.login.models.ApiUser
import io.reactivex.Single

interface IAuthenticationDataSource {

    fun login(apiCredentials: HashMap<String, String>): Single<ApiResponse>

    fun register(user: ApiUser): Single<ApiResponse>

}