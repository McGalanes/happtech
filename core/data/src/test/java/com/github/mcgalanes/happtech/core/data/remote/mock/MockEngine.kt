package com.github.mcgalanes.happtech.core.data.remote.mock

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

fun mockEngine(
    content: ByteReadChannel,
    statusCode: HttpStatusCode,
    headers: Headers = headersOf(HttpHeaders.ContentType, "application/json"),
    onInterceptUrl: ((Url) -> Unit) = { },
) = MockEngine {
    onInterceptUrl(it.url)
    respond(
        content = content,
        status = statusCode,
        headers = headers,
    )
}
