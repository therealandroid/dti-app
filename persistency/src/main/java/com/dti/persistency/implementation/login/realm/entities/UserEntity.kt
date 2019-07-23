package com.dti.persistency.implementation.login.realm.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserEntity(
    @PrimaryKey
    var id: String = "",
    var username: String = ""
) : RealmObject()