package com.github.mcgalanes.happtech.core.data.injection

import com.github.mcgalanes.happtech.core.data.remote.api.RijksMuseumApi
import com.github.mcgalanes.happtech.core.data.repository.DefaultRijksMuseumRepository
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(RijksMuseumApi::Default) bind RijksMuseumApi::class
    singleOf(::DefaultRijksMuseumRepository) bind RijksMuseumRepository::class
}
