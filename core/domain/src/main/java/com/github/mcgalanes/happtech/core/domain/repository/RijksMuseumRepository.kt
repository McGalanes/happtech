package com.github.mcgalanes.happtech.core.domain.repository

import com.github.mcgalanes.happtech.core.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow

interface RijksMuseumRepository {
    fun getCollectionFlow(): Flow<List<ArtObject>>
    suspend fun refreshAllCollection(query: String? = null): Result<Unit>
}
