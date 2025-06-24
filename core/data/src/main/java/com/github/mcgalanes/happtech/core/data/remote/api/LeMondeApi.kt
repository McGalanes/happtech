package com.github.mcgalanes.happtech.core.data.remote.api

import com.github.mcgalanes.happtech.core.data.remote.response.LeMondeLiveArticlesResponse
import com.github.mcgalanes.happtech.core.network.apiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface LeMondeApi {
    suspend fun getLiveArticles(): Result<LeMondeLiveArticlesResponse>

    class Default(private val client: HttpClient) : LeMondeApi {

        override suspend fun getLiveArticles(): Result<LeMondeLiveArticlesResponse> {
            return apiCall { client.get("en_continu/index.json") }
        }
    }
}
