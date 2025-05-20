package com.github.mcgalanes.happtech.core.network.injection

import com.github.mcgalanes.happtech.core.network.createRijksMuseumHttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> { CIO.create() }
    single {
        createRijksMuseumHttpClient(
            engine = get(),
            apiKey = "rIl6yb6x", //TODO: extract and inject api key
        )
    }
}
