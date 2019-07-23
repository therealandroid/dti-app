package com.therealdroid.github.company.register

import com.therealdroid.github.network.implementation.login.IAuthenticationDataSource
import com.therealdroid.github.network.implementation.login.AuthenticationNetworkDataSource
import com.therealdroid.github.network.implementation.login.models.ApiResponse
import com.therealdroid.github.network.implementation.login.models.ApiUser
import io.reactivex.Single

class RegisterRepository(var IAuthenticationDataSource: IAuthenticationDataSource ) {

    //A better approach is to use a mapper to avoid passing models from network module to the App module
    //but to make it more simple I choose to continue using the same model
    fun register(username: String, password: String): Single<ApiResponse> {
        return IAuthenticationDataSource.register(ApiUser(username, password))
    }

}