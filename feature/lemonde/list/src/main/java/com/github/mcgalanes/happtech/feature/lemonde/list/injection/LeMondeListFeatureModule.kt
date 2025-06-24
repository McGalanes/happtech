package com.github.mcgalanes.happtech.feature.lemonde.list.injection

import com.github.mcgalanes.happtech.feature.lemonde.list.LeMondeListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val leMondeListFeatureModule = module {
    viewModelOf(::LeMondeListViewModel)
}
