package com.github.mcgalanes.happtech.core.data.repository

import com.github.mcgalanes.happtech.core.data.remote.api.RijksMuseumApi
import com.github.mcgalanes.happtech.core.data.remote.response.toDomain
import com.github.mcgalanes.happtech.core.domain.model.ArtObject
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultRijksMuseumRepository(
    private val api: RijksMuseumApi,
) : RijksMuseumRepository {
    private val collectionFlow: MutableStateFlow<List<ArtObject>> = MutableStateFlow(emptyList())

    override fun getCollectionFlow(): Flow<List<ArtObject>> = collectionFlow

    override suspend fun refreshAllCollection(query: String?): Result<Unit> {
        return api.getCollection(query)
            .map { it.toDomain() }
            .onSuccess { artObjectList ->
                collectionFlow.value = artObjectList
            }
            .map { Unit }
    }
}
