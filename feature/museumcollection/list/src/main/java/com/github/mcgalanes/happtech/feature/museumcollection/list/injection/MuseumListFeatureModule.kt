package com.github.mcgalanes.happtech.feature.museumcollection.list.injection

import com.github.mcgalanes.happtech.feature.museumcollection.list.MuseumListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val museumListFeatureModule = module {
    viewModelOf(::MuseumListViewModel)
}
