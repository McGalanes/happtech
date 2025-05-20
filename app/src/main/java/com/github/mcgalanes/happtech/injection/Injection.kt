package com.github.mcgalanes.happtech.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module

internal fun Application.initKoin() {
    startKoin {
        AndroidLogger()
        androidContext(this@initKoin)
        modules(getModules())
    }
}

private fun getModules(): List<Module> = listOf(
)
