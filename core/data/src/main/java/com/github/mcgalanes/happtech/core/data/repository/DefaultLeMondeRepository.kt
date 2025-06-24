package com.github.mcgalanes.happtech.core.data.repository

import com.github.mcgalanes.happtech.core.data.remote.api.LeMondeApi
import com.github.mcgalanes.happtech.core.data.remote.response.toDomain
import com.github.mcgalanes.happtech.core.domain.model.LeMondeElement
import com.github.mcgalanes.happtech.core.domain.repository.LeMondeRepository

class DefaultLeMondeRepository(
    private val api: LeMondeApi,
) : LeMondeRepository {

    override suspend fun getElements(): Result<List<LeMondeElement>> {
        return api.getLiveArticles().map { it.toDomain() }
    }
}
