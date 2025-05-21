package com.github.mcgalanes.happtech.core.data.fake

import com.github.mcgalanes.happtech.core.data.remote.api.RijksMuseumApi
import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse

class FakeRijksMuseumApi(
    private val mockGetCollection: () -> Result<CollectionResponse>,
) : RijksMuseumApi {
    override suspend fun getCollection(query: String?): Result<CollectionResponse> {
        return mockGetCollection()
    }
}
