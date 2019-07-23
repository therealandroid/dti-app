package com.therealdroid.github.network.implementation.login

import com.therealdroid.github.network.implementation.login.models.ApiResponse
import com.therealdroid.github.network.implementation.login.models.ApiUser
import com.therealdroid.github.network.implementation.login.routes.AuthRoute
import io.reactivex.Single

/*
 * This class is responsible to handle networking operations
 *
 */
open class AuthenticationNetworkDataSource : IAuthenticationDataSource {

    private val serviceRoute: AuthRoute = RetrofitManager.provideService(AuthRoute::class.java)

    override fun register(user: ApiUser): Single<ApiResponse> {
        return serviceRoute.register(user)
    }

    override fun login(apiCredentials: HashMap<String, String>): Single<ApiResponse> {
        return serviceRoute.login(apiCredentials)
    }

}