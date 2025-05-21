package com.github.mcgalanes.happtech.feature.museumcollection.fake

import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import com.github.mcgalanes.happtech.core.domain.repository.RijksMuseumRepository
import kotlinx.coroutines.flow.Flow

class FakeRijksMuseumRepository : RijksMuseumRepository {
    private var detailData: ArtObjectDetail? = null

    override fun getCollectionFlow(): Flow<List<ArtObjectLight>> = throw NotImplementedError()

    override suspend fun refreshAllCollection(query: String?): Result<Unit> {
        throw NotImplementedError()
    }

    override suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetail> {
        detailData?.let {
            return Result.success(it)
        } ?: return Result.failure(Exception("No detail data available"))
    }

    fun setDataForDetail(artObjectDetail: ArtObjectDetail?) {
        detailData = artObjectDetail
    }
}
