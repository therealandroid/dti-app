package com.therealdroid.github.company.login

import com.dti.persistency.implementation.login.IUserPersistenceDataSource
import com.dti.persistency.implementation.login.UserPersistenceDataSource
import com.dti.persistency.implementation.login.realm.entities.UserEntity
import com.therealdroid.github.network.implementation.login.IAuthenticationDataSource
import com.therealdroid.github.network.implementation.login.AuthenticationNetworkDataSource
import com.therealdroid.github.network.implementation.login.models.ApiResponse
import io.reactivex.Single
import java.util.*
import kotlin.collections.HashMap

class LoginRepository {

    private val authenticationNetworkDataSource: IAuthenticationDataSource = AuthenticationNetworkDataSource()
    private val userPersistenceDataSource: IUserPersistenceDataSource = UserPersistenceDataSource()

    //A better approach is to use a mapper to avoid passing models from network module to the App module
    //but to make it more simple I choose to continue using the same model
    fun login(username: String, password: String): Single<ApiResponse> {
        val map = HashMap<String, String>()
        map["username"] = username
        map["password"] = password

        return authenticationNetworkDataSource
            .login(map)
            .doOnSuccess { response ->
                //Let's save the user to database
                val user = userPersistenceDataSource.saveUser(
                    UserEntity(
                        UUID.randomUUID().toString(),
                        username
                    )
                )

                if (response.success) {
                    response.message = user.username
                }
            }
    }

}