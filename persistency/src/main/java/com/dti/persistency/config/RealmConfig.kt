package com.dti.persistency.config

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Call initialize to init realm from you application app module
 */
object RealmConfig {

    fun init(context: Context) {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .name("dti_db.realm")
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()//replace it by your class
            .build()

        Realm.setDefaultConfiguration(config)
    }
}