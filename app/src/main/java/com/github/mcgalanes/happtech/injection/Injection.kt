package com.github.mcgalanes.happtech.injection

import android.app.Application
import com.github.mcgalanes.happtech.core.data.injection.dataModule
import com.github.mcgalanes.happtech.core.network.injection.networkModule
import com.github.mcgalanes.happtech.feature.museumcollection.detail.injection.museumDetailFeatureModule
import com.github.mcgalanes.happtech.feature.museumcollection.list.injection.museumListFeatureModule
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

private fun getModules(): List<Module> =
    listOf(
        dataModule,
        networkModule,
        museumListFeatureModule,
        museumDetailFeatureModule,
    )
