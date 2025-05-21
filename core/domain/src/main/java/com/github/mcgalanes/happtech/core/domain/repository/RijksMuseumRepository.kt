package com.github.mcgalanes.happtech.core.domain.repository

import com.github.mcgalanes.happtech.core.domain.model.ArtObject
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import kotlinx.coroutines.flow.Flow

interface RijksMuseumRepository {
    fun getCollectionFlow(): Flow<List<ArtObject>>
    suspend fun refreshAllCollection(query: String? = null): Result<Unit>

    suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail>
}
