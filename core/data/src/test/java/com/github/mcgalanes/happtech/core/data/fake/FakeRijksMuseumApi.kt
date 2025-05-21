package com.github.mcgalanes.happtech.core.data.fake

import com.github.mcgalanes.happtech.core.data.remote.api.RijksMuseumApi
import com.github.mcgalanes.happtech.core.data.remote.response.ArtObjectDetailResponse
import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse

class FakeRijksMuseumApi(
    private val mockGetCollection: () -> Result<CollectionResponse>,
    private val mockGetArtObjectDetail: () -> Result<ArtObjectDetailResponse> = { throw NotImplementedError() },
) : RijksMuseumApi {
    override suspend fun getCollection(query: String?): Result<CollectionResponse> {
        return mockGetCollection()
    }

    override suspend fun getArtObjectDetail(objectNumber: String): Result<ArtObjectDetailResponse> {
        return mockGetArtObjectDetail()
    }
}
