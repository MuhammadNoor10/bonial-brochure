package com.noor.compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BonialApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}