package com.github.mcgalanes.happtech.core.data.remote.api

import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse
import com.github.mcgalanes.happtech.core.network.apiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface RijksMuseumApi {
    suspend fun getCollection(query: String? = null): Result<CollectionResponse>

    class Default(private val client: HttpClient) : RijksMuseumApi {

        override suspend fun getCollection(query: String?): Result<CollectionResponse> = apiCall {
            client.get("collection") {
                parameter(QUERY_PARAM_PAGE_SIZE, 100)
                query?.let { parameter(QUERY_PARAM_QUERY, it) }
            }
        }

        companion object {
            private const val QUERY_PARAM_PAGE_SIZE = "ps"
            private const val QUERY_PARAM_QUERY = "q"
        }
    }
}
