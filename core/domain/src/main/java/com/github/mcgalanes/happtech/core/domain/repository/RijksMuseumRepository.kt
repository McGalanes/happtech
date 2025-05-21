package com.github.mcgalanes.happtech.core.domain.repository

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import kotlinx.coroutines.flow.Flow

interface RijksMuseumRepository {
    fun getCollectionFlow(): Flow<List<ArtObjectLight>>
    suspend fun refreshAllCollection(query: String? = null): Result<Unit>

    suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail>
}
