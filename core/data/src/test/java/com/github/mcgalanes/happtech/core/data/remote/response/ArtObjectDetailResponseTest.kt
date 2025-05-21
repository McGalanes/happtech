package com.github.mcgalanes.happtech.core.data.remote.response

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test


class ArtObjectDetailMapperTest {

    @Test
    fun `toDomain should throws when artObject is null`() {
        // GIVEN
        val response = ArtObjectDetailResponse(artObject = null)

        // WHEN THEN
        assertThrows(IllegalArgumentException::class.java) {
            response.toDomain()
        }
    }

    @Test
    fun `toDomain should throws when webImage is present but width is null`() {
        // GIVEN
        val response = ArtObjectDetailResponse(
            artObject = ArtObjectDetailResponse.ArtObject(
                objectNumber = "FAKE-1234",
                title = "Fake Art Object",
                description = "This is a fake art object for testing purposes.",
                objectCollection = listOf("Fake Collection"),
                techniques = listOf("Fake Technique"),
                principalMakers = listOf(
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "John Doe",
                        dateOfBirth = "1900",
                        dateOfDeath = "2000",
                    ),
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "Sonny Moore",
                        dateOfBirth = "1950",
                        dateOfDeath = "1978",
                    ),
                ),
                dating = ArtObjectDetailResponse.ArtObject.Dating(
                    presentingDate = "2023-10-01",
                ),
                documentation = listOf("Fake Document 1", "Fake Document 2"),
                dimensions = listOf(
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "height",
                        value = "300",
                    ),
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "width",
                        value = "200",
                    ),
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "depth",
                        value = "100",
                    ),
                ),
                webImage = ArtObjectDetailResponse.ArtObject.Image(
                    guid = "guid",
                    width = null,
                    height = 600,
                    url = "https://example.com/fake-image.jpg",
                ),
            ),
        )

        // WHEN THEN
        assertThrows(IllegalArgumentException::class.java) {
            response.toDomain()
        }
    }

    @Test
    fun `toDomain should throws when webImage is present but height is null`() {
        // GIVEN
        val response = ArtObjectDetailResponse(
            artObject = ArtObjectDetailResponse.ArtObject(
                objectNumber = "FAKE-1234",
                title = "Fake Art Object",
                description = "This is a fake art object for testing purposes.",
                objectCollection = listOf("Fake Collection"),
                techniques = listOf("Fake Technique"),
                principalMakers = listOf(
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "John Doe",
                        dateOfBirth = "1900",
                        dateOfDeath = "2000",
                    ),
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "Sonny Moore",
                        dateOfBirth = "1950",
                        dateOfDeath = "1978",
                    ),
                ),
                dating = ArtObjectDetailResponse.ArtObject.Dating(
                    presentingDate = "2023-10-01",
                ),
                documentation = listOf("Fake Document 1", "Fake Document 2"),
                dimensions = listOf(
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "height",
                        value = "300",
                    ),
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "width",
                        value = "200",
                    ),
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "depth",
                        value = "100",
                    ),
                ),
                webImage = ArtObjectDetailResponse.ArtObject.Image(
                    guid = "guid",
                    width = 800,
                    height = null,
                    url = "https://example.com/fake-image.jpg",
                ),
            ),
        )

        // WHEN THEN
        assertThrows(IllegalArgumentException::class.java) {
            response.toDomain()
        }
    }

    @Test
    fun `toDomain should map height and with to ration`() {
        // GIVEN
        val response = ArtObjectDetailResponse(
            artObject = ArtObjectDetailResponse.ArtObject(
                objectNumber = "FAKE-1234",
                title = "Fake Art Object",
                description = "This is a fake art object for testing purposes.",
                objectCollection = listOf("Fake Collection"),
                techniques = listOf("Fake Technique"),
                principalMakers = listOf(
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "John Doe",
                        dateOfBirth = "1900",
                        dateOfDeath = "2000",
                    ),
                    ArtObjectDetailResponse.ArtObject.PrincipalMaker(
                        name = "Sonny Moore",
                        dateOfBirth = "1950",
                        dateOfDeath = "1978",
                    ),
                ),
                dating = ArtObjectDetailResponse.ArtObject.Dating(
                    presentingDate = "2023-10-01",
                ),
                documentation = listOf("Fake Document 1", "Fake Document 2"),
                dimensions = listOf(
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "height",
                        value = "300",
                    ),
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "width",
                        value = "200",
                    ),
                    ArtObjectDetailResponse.ArtObject.Dimension(
                        unit = "cm",
                        type = "depth",
                        value = "100",
                    ),
                ),
                webImage = ArtObjectDetailResponse.ArtObject.Image(
                    guid = "guid",
                    width = 2520,
                    height = 720,
                    url = "https://example.com/fake-image.jpg",
                ),
            ),
        )

        // WHEN
        val result = response.toDomain()

        // THEN
        assertEquals(2520 / 720f, result.image!!.ratio)
    }
}
