package com.github.mcgalanes.happtech.core.data.repository

import com.github.mcgalanes.happtech.core.data.fake.FakeRijksMuseumApi
import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse
import com.github.mcgalanes.happtech.core.data.remote.response.toDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DefaultRijksMuseumRepositoryTest {

    @Test
    fun `refreshAllCollection, should succeed when api is OK`() = runTest {
        // GIVEN
        val api =
            FakeRijksMuseumApi(
                mockGetCollection = {
                    Result.success(fakeCollectionResponseData())
                },
            )

        val repository = DefaultRijksMuseumRepository(api)

        // WHEN
        val result = repository.refreshAllCollection()

        // THEN
        Assert.assertTrue(result.isSuccess)
    }

    @Test
    fun `refreshAllCollection, should fail when api fails`() = runTest {
        // GIVEN
        val api =
            FakeRijksMuseumApi(
                mockGetCollection = {
                    Result.failure(Exception("API error"))
                },
            )

        val repository = DefaultRijksMuseumRepository(api)

        // WHEN
        val result = repository.refreshAllCollection()

        // THEN
        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `getCollectionFlow, should return empty list when no data is available`() = runTest {
        // GIVEN
        val api =
            FakeRijksMuseumApi(
                mockGetCollection = {
                    Result.success(fakeCollectionResponseData())
                },
            )

        val repository = DefaultRijksMuseumRepository(api)

        // WHEN
        val flow = repository.getCollectionFlow()

        // THEN
        Assert.assertTrue(flow.first().isEmpty())
    }

    @Test
    fun `getCollectionFlow, should return data when it was refreshed`() = runTest {
        // GIVEN
        val response = fakeCollectionResponseData()
        val api = FakeRijksMuseumApi(mockGetCollection = { Result.success(response) })

        val repository = DefaultRijksMuseumRepository(api)

        repository.refreshAllCollection()

        // WHEN
        val flow = repository.getCollectionFlow()

        // THEN
        Assert.assertEquals(response.toDomain(), flow.first())
    }

    private fun fakeCollectionResponseData() =
        CollectionResponse(
            artObjects = listOf(
                CollectionResponse.ArtObject(
                    id = "1",
                    title = "Title 1",
                    longTitle = "Long Title 1",
                    headerImage = CollectionResponse.ArtObject.Image(
                        guid = "guid1",
                        url = "https://example.com/image1.jpg",
                        width = 100,
                        height = 100,
                    ),
                    principalOrFirstMaker = "Maker 1",
                    webImage = CollectionResponse.ArtObject.Image(
                        guid = "guid2",
                        url = "https://example.com/image2.jpg",
                        width = 100,
                        height = 100,
                    ),
                    hasImage = true,
                    productionPlaces = listOf("Place 1"),
                    objectNumber = "Object 1",
                    showImage = true,
                ),
            ),
            count = 0,
        )
}
