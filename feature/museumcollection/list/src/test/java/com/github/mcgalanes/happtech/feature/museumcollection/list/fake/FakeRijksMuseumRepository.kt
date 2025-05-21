package com.github.mcgalanes.happtech.feature.museumcollection.list.fake

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeRijksMuseumRepository : RijksMuseumRepository {

    private val _collectionFlow = MutableStateFlow<List<ArtObjectLight>>(emptyList())
    private var collectionData: List<ArtObjectLight> = emptyList()

    private var shouldRefreshSucceed: Boolean = true

    override fun getCollectionFlow(): Flow<List<ArtObjectLight>> = _collectionFlow.asStateFlow()

    override suspend fun refreshAllCollection(query: String?): Result<Unit> {
        return if (shouldRefreshSucceed) {
            _collectionFlow.value = collectionData
            Result.success(Unit)
        } else {
            Result.failure(Exception("Fake refresh failure"))
        }
    }

    override suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail> {
        throw NotImplementedError()
    }

    fun setDataForRefresh(artObjects: List<ArtObjectLight>) {
        collectionData = artObjects
    }

    fun setShouldRefreshSucceed(shouldRefreshSucceed: Boolean) {
        this.shouldRefreshSucceed = shouldRefreshSucceed
    }
}
