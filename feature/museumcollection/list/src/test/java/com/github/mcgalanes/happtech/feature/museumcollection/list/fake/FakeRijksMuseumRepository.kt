package com.github.mcgalanes.happtech.feature.museumcollection.list.fake

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeRijksMuseumRepository() : RijksMuseumRepository {

    private var refreshSucceed: Boolean = true
    private val dataFlow = MutableStateFlow<List<ArtObjectLight>>(emptyList())

    override fun getCollectionFlow(): Flow<List<ArtObjectLight>> {
        return dataFlow
    }

    override suspend fun refreshCollection(query: String?): Result<Unit> {
        return if (refreshSucceed) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Refresh failed"))
        }
    }

    override suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail> {
        throw NotImplementedError()
    }

    fun mockRefreshCollectionSucceed(succeed: Boolean) {
        refreshSucceed = succeed
    }

    fun mockSetCollectionData(data: List<ArtObjectLight>) {
        dataFlow.value = data
    }
}
