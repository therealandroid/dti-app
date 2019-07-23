package com.dti.persistency.implementation.login.realm.dao

import com.dti.persistency.implementation.login.realm.entities.UserEntity
import io.realm.Realm

class UserDao {

    fun findUserByUsername(username: String): UserEntity? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(UserEntity::class.java).equalTo("username", username).findFirst()

        return if (result != null) {
            realm.copyFromRealm(result)
        } else {
            null
        }
    }

    fun insertUser(userEntity: UserEntity): UserEntity {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val result = realm.copyToRealmOrUpdate(userEntity)
        realm.commitTransaction()

        return realm.copyFromRealm(result)
    }

    fun removeUserByUsername(username: String): Boolean {
        val realm = Realm.getDefaultInstance()
        //search for the user

        realm.executeTransaction {
            val result = realm.where(UserEntity::class.java).equalTo("username", username).findAll()
            result.deleteAllFromRealm()
        }

        //remove

        //check if the user was removed making another select
        val checkUserWasRemoved = realm.where(UserEntity::class.java).equalTo("username", username).findFirst()

        return checkUserWasRemoved == null

    }
}