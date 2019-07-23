package com.dti.persistency.implementation.login

import com.dti.persistency.implementation.login.realm.entities.UserEntity
import com.dti.persistency.implementation.login.realm.dao.UserDao

class UserPersistenceDataSource : IUserPersistenceDataSource {

    private var userDao = UserDao()


    override fun getUser(username: String): UserEntity? {
        return userDao.findUserByUsername(username)
    }

    override fun deleteUser(username: String): Boolean {
        return userDao.removeUserByUsername(username)
    }

    override fun saveUser(userEntity: UserEntity): UserEntity {
        return userDao.insertUser(userEntity)
    }

}