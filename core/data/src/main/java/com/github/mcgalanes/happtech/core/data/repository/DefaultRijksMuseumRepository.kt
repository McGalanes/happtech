package com.github.mcgalanes.happtech.core.data.repository

import com.github.mcgalanes.happtech.core.data.remote.api.RijksMuseumApi
import com.github.mcgalanes.happtech.core.data.remote.response.ArtObjectDetailResponse
import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse
import com.github.mcgalanes.happtech.core.data.remote.response.toDomain
import com.github.mcgalanes.happtech.core.database.dao.ArtObjectLightDao
import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity
import com.github.mcgalanes.happtech.core.database.mapper.toDomain
import com.github.mcgalanes.happtech.core.database.mapper.toEntity
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultRijksMuseumRepository(
    private val api: RijksMuseumApi,
    private val local: ArtObjectLightDao,
) : RijksMuseumRepository {

    override fun getCollectionFlow(): Flow<List<ArtObjectLight>> {
        return local.getAll().map { it.map(ArtObjectLightEntity::toDomain) }
    }

    override suspend fun refreshCollection(query: String?): Result<Unit> {
        return api.getCollection(query).map(CollectionResponse::toDomain)
            .mapCatching { artObjectList ->
                val entities = artObjectList.map { it.toEntity(query.orEmpty()) }
                local.clear()
                local.insertAll(entities)
            }
    }

    override suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail> {
        return api.getArtObjectDetail(objectNumber).mapCatching(ArtObjectDetailResponse::toDomain)
    }
}
