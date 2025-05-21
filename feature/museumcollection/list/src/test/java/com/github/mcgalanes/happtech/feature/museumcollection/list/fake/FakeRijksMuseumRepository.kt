package com.github.mcgalanes.happtech.feature.museumcollection.list.fake

import com.github.mcgalanes.happtech.core.domain.model.ArtObject
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeRijksMuseumRepository : RijksMuseumRepository {

    private val _collectionFlow = MutableStateFlow<List<ArtObject>>(emptyList())

    private var data: List<ArtObject> = emptyList()
    private var shouldRefreshSucceed: Boolean = true

    override fun getCollectionFlow(): Flow<List<ArtObject>> = _collectionFlow.asStateFlow()

    override suspend fun refreshAllCollection(query: String?): Result<Unit> {
        return if (shouldRefreshSucceed) {
            _collectionFlow.value = data
            Result.success(Unit)
        } else {
            Result.failure(Exception("Fake refresh failure"))
        }
    }

    fun setDataForRefresh(artObjects: List<ArtObject>) {
        data = artObjects
    }

    fun setShouldRefreshSucceed(shouldRefreshSucceed: Boolean) {
        this.shouldRefreshSucceed = shouldRefreshSucceed
    }
}
