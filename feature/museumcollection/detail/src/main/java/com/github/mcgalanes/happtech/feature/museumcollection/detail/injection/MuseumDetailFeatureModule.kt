package com.github.mcgalanes.happtech.feature.museumcollection.detail.injection

import com.github.mcgalanes.happtech.feature.museumcollection.detail.MuseumDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val museumDetailFeatureModule = module {
    viewModelOf(::MuseumDetailViewModel)
}
