package com.therealdroid.github.company.application

import android.app.Application
import com.dti.persistency.config.RealmConfig

class CompanyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        RealmConfig.init(this)
    }
}