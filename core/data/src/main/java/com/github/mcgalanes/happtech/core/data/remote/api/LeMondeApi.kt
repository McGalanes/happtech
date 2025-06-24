package com.github.mcgalanes.happtech.core.data.remote.api

import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse
import com.github.mcgalanes.happtech.core.network.apiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface LeMondeApi {
    suspend fun getArticles(query: String? = null): Result<CollectionResponse>

    class Default(private val client: HttpClient) : LeMondeApi {
        override suspend fun getArticles(query: String?): Result<CollectionResponse> {
            return apiCall { client.get("en_continu/index.json") }
        }
    }
}
