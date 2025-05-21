package com.github.mcgalanes.happtech.core.data.fake

import com.github.mcgalanes.happtech.core.database.dao.ArtObjectLightDao
import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeArtObjectLightDao(
    preloaded: List<ArtObjectLightEntity> = emptyList(),
) : ArtObjectLightDao {
    private val storage = preloaded.toMutableList()

    override suspend fun clear() {
        storage.clear()
    }

    override fun getAll(): Flow<List<ArtObjectLightEntity>> {
        return flowOf(storage)
    }

    override suspend fun insertAll(entities: List<ArtObjectLightEntity>) {
        storage.addAll(entities)
    }
}
