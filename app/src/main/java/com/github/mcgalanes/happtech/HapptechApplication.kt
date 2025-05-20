package com.github.mcgalanes.happtech

import android.app.Application
import com.github.mcgalanes.happtech.injection.initKoin

class HapptechApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
