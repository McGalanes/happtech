package com.github.mcgalanes.happtech.core.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> apiCall(
    noinline call: suspend () -> HttpResponse,
): Result<T> {
    return runCatching {
        val response = call()
        val status = response.status

        if (!status.isSuccess()) {
            throw ApiException(
                code = status.value,
                description = status.description,
            )
        }

        response.body<T>()
    }
}
