package com.github.mcgalanes.happtech.core.data.repository

import com.github.mcgalanes.happtech.core.data.fake.FakeArtObjectLightDao
import com.github.mcgalanes.happtech.core.data.fake.FakeRijksMuseumApi
import com.github.mcgalanes.happtech.core.data.remote.response.ArtObjectDetailResponse
import com.github.mcgalanes.happtech.core.data.remote.response.CollectionResponse
import com.github.mcgalanes.happtech.core.data.remote.response.toDomain
import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity
import com.github.mcgalanes.happtech.core.database.mapper.toDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DefaultRijksMuseumRepositoryTest {

    @Test
    fun `getCollectionFlow should return a flow of ArtObjectLight`() = runTest {
        // GIVEN
        val entities = createFakeEntities()
        val local = FakeArtObjectLightDao(preloaded = entities)
        val api = FakeRijksMuseumApi()

        val repository = DefaultRijksMuseumRepository(api, local)

        // WHEN
        val flow = repository.getCollectionFlow()

        // THEN
        val expected = entities.map(ArtObjectLightEntity::toDomain)
        Assert.assertEquals(expected, flow.first())
    }

    @Test
    fun `refreshCollection should store in local when remote succeed`() = runTest {
        // GIVEN
        val response = createFakeCollectionResponse()
        val local = FakeArtObjectLightDao()
        val api = FakeRijksMuseumApi(mockGetCollection = { Result.success(response) })

        val repository = DefaultRijksMuseumRepository(api, local)

        val query = "France"

        // WHEN
        repository.refreshCollection(query)

        // THEN
        val expected = response.artObjects!!.map(CollectionResponse.ArtObject::toDomain)
        val actual = repository.getCollectionFlow().first()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `refreshCollection should fail when remote fails`() = runTest {
        // GIVEN
        val local = FakeArtObjectLightDao()
        val api =
            FakeRijksMuseumApi(mockGetCollection = { Result.failure(Exception("Network error")) })

        val repository = DefaultRijksMuseumRepository(api, local)

        // WHEN
        val result = repository.refreshCollection(null)

        // THEN
        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `getArtObjectDetail should return data when success`() = runTest {
        // GIVEN
        val response = createFakeArtObjectDetailResponse()
        val local = FakeArtObjectLightDao()
        val api = FakeRijksMuseumApi(mockGetArtObjectDetail = { Result.success(response) })

        val repository = DefaultRijksMuseumRepository(api, local)

        // WHEN
        val result = repository.getArtObjectDetail(objectNumber = "object_123")

        // THEN
        Assert.assertEquals(response.toDomain(), result.getOrThrow())
    }

    @Test
    fun `getArtObjectDetail should return error when failure`() = runTest {
        // GIVEN
        val local = FakeArtObjectLightDao()
        val api =
            FakeRijksMuseumApi(mockGetArtObjectDetail = { Result.failure(Exception("Network error")) })

        val repository = DefaultRijksMuseumRepository(api, local)

        // WHEN
        val result = repository.getArtObjectDetail(objectNumber = "object_123")

        // THEN
        Assert.assertTrue(result.isFailure)
    }

    private fun createFakeEntities(): List<ArtObjectLightEntity> = listOf(
        ArtObjectLightEntity(
            objectNumber = "A1",
            title = "Art Title 1",
            hasImage = true,
            imageUrl = "https://example.com/image1.jpg",
            imageRatio = 1.5f,
            query = "query1",
        ),
        ArtObjectLightEntity(
            objectNumber = "A2",
            title = "Art Title 2",
            hasImage = false,
            imageUrl = "",
            imageRatio = 1.0f,
            query = "query2",
        ),
        ArtObjectLightEntity(
            objectNumber = "A3",
            title = "Art Title 3",
            hasImage = true,
            imageUrl = "https://example.com/image3.jpg",
            imageRatio = 1.2f,
            query = "query3",
        ),
        ArtObjectLightEntity(
            objectNumber = "A4",
            title = "Art Title 4",
            hasImage = true,
            imageUrl = "https://example.com/image4.jpg",
            imageRatio = 1.8f,
            query = "query4",
        ),
        ArtObjectLightEntity(
            objectNumber = "A5",
            title = "Art Title 5",
            hasImage = false,
            imageUrl = "",
            imageRatio = 1.0f,
            query = "query5",
        ),
    )

    private fun createFakeCollectionResponse(): CollectionResponse = CollectionResponse(
        artObjects = listOf(
            CollectionResponse.ArtObject(
                id = "1",
                objectNumber = "objectNumber",
                title = "title",
                hasImage = true,
                principalOrFirstMaker = "principalOrFirstMaker",
                longTitle = "longTitle",
                showImage = true,
                webImage = CollectionResponse.ArtObject.Image(
                    guid = "guid",
                    width = 100,
                    height = 200,
                    url = "url",
                ),
                headerImage = null,
                productionPlaces = null,
            ),
            CollectionResponse.ArtObject(
                id = "2",
                objectNumber = "objectNumber2",
                title = "title2",
                hasImage = false,
                principalOrFirstMaker = "principalOrFirstMaker2",
                longTitle = "longTitle2",
                showImage = false,
                webImage = null,
                headerImage = null,
                productionPlaces = null,
            ),
        ),
        count = 1,
    )

    private fun createFakeArtObjectDetailResponse(): ArtObjectDetailResponse {
        return ArtObjectDetailResponse(
            artObject = ArtObjectDetailResponse.ArtObject(
                objectNumber = "123",
                title = "Fake Title",
                description = "Fake Description",
                objectCollection = listOf("Collection 1", "Collection 2"),
                techniques = listOf("Technique 1", "Technique 2"),
                principalMakers = listOf(
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "Maker 1",
                        dateOfBirth = "1900",
                        dateOfDeath = "2000"
                    )
                ),
                dating = ArtObjectDetailResponse.ArtObject.Dating(presentingDate = "2000"),
                documentation = listOf("Doc 1", "Doc 2"),
                dimensions = listOf(
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "Height",
                        value = "100"
                    )
                ),
                webImage = ArtObjectDetailResponse.ArtObject.Image(
                    guid = "image_guid",
                    width = 800,
                    height = 600,
                    url = "http://example.com/image.jpg"
                )
            )
        )
    }
}
