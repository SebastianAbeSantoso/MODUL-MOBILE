package com.example.modul4compose

import android.app.Application
import timber.log.Timber

class TimberApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}