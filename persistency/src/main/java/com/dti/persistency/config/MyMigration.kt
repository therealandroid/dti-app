package com.dti.persistency.config

import io.realm.DynamicRealm
import io.realm.RealmMigration

class MyMigration: RealmMigration {


    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        //create your migrations
    }
}