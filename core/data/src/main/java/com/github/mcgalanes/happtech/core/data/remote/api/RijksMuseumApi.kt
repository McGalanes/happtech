package com.github.mcgalanes.happtech.core.data.remote.api

import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse
import com.github.mcgalanes.happtech.core.network.apiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface RijksMuseumApi {
    suspend fun getCollection(): Result<CollectionResponse>

    class Default(private val client: HttpClient) : RijksMuseumApi {

        override suspend fun getCollection(): Result<CollectionResponse> =
            apiCall {
                client.get("collection") {
                    parameter("ps", 100)
                    parameter("q", "FRANCE")
                }
            }
    }
}
