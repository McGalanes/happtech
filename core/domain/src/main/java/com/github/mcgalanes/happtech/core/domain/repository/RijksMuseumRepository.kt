package com.github.mcgalanes.happtech.core.domain.repository

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import kotlinx.coroutines.flow.Flow

interface RijksMuseumRepository {
    fun getCollectionFlow(): Flow<List<ArtObjectLight>>

    suspend fun refreshCollection(query: String?): Result<Unit>

    suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail>
}
