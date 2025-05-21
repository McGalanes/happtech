package com.github.mcgalanes.happtech.core.data.remote.response

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CollectionResponseTest {

    @Test
    fun `toDomain should throw when artObjects is null`() {
        // GIVEN
        val response = CollectionResponse(artObjects = null, count = 24)

        // WHEN THEN
        assertThrows(IllegalArgumentException::class.java) {
            response.toDomain()
        }
    }

    @Test
    fun `Image toDomain should throw when width is null`() {
        // GIVEN
        val image = CollectionResponse.ArtObject.Image(
            guid = "guid",
            url = "https://example.com/image.jpg",
            width = null,
            height = 600,
        )

        // WHEN THEN
        assertThrows(IllegalArgumentException::class.java) {
            image.toDomain()
        }
    }

    @Test
    fun `Image toDomain should throw when height is null`() {
        // GIVEN
        val image = CollectionResponse.ArtObject.Image(
            guid = "guid",
            url = "https://example.com/image.jpg",
            width = 800,
            height = null,
        )

        // WHEN THEN
        assertThrows(IllegalArgumentException::class.java) {
            image.toDomain()
        }
    }

    @Test
    fun `Image toDomain should return null when guid is null`() {
        // GIVEN
        val image = CollectionResponse.ArtObject.Image(
            guid = null,
            url = "https://example.com/image.jpg",
            width = 800,
            height = 600,
        )

        // WHEN
        val result = image.toDomain()

        // THEN
        assertEquals(null, result)
    }

    @Test
    fun `Image toDomain should calculate correct ratio`() {
        // GIVEN
        val image = CollectionResponse.ArtObject.Image(
            guid = "some-guid",
            url = "https://example.com/image.jpg",
            width = 1200,
            height = 600,
        )

        // WHEN
        val result = image.toDomain()

        // THEN
        assertEquals(2.0f, result!!.ratio)
    }
}
