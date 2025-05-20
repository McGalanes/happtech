package com.github.mcgalanes.happtech.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val QUERY_PARAM_API_KEY = "key"

fun createRijksMuseumHttpClient(
    engine: HttpClientEngine,
    apiKey: String,
): HttpClient =
    HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                },
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        defaultRequest {
            url("https://www.rijksmuseum.nl/api/en/")
            url.parameters.append(name = QUERY_PARAM_API_KEY, value = apiKey)
        }
    }
