package com.dti.persistency.implementation.login

import com.dti.persistency.implementation.login.realm.entities.UserEntity

interface IUserPersistenceDataSource {


    fun getUser(username: String): UserEntity?

    fun deleteUser(username: String): Boolean

    fun saveUser(userEntity: UserEntity): UserEntity
}