package com.github.mcgalanes.happtech.core.database.mapper

import com.github.mcgalanes.happtech.core.database.entity.ArtObjectLightEntity
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ArtObjectLightMapperTest {

    @Test
    fun `toDomain should return null webImage when imageUrl is null`() {
        val entity = ArtObjectLightEntity(
            objectNumber = "A1",
            title = "Art Title",
            hasImage = true,
            imageUrl = null,
            imageRatio = 1.5f,
            query = "query",
        )
        assertNull(entity.toDomain().webImage)
    }

    @Test
    fun `toDomain should return null webImage when imageRatio is null`() {
        val entity = ArtObjectLightEntity(
            objectNumber = "A2",
            title = "Art Title",
            hasImage = true,
            imageUrl = "url",
            imageRatio = null,
            query = "query",
        )
        assertNull(entity.toDomain().webImage)
    }

    @Test
    fun `toDomain should return valid webImage when imageUrl and imageRatio are present`() {
        val entity = ArtObjectLightEntity(
            objectNumber = "A1",
            title = "Art Title",
            hasImage = true,
            imageUrl = "url",
            imageRatio = 1.5f,
            query = "query",
        )
        val domain = entity.toDomain()
        assertNotNull(domain.webImage)
    }

    @Test
    fun `toDomain should map webImage fields correctly`() {
        val entity = ArtObjectLightEntity(
            objectNumber = "A1",
            title = "Art Title",
            hasImage = true,
            imageUrl = "url",
            imageRatio = 1.5f,
            query = "query",
        )
        val domain = entity.toDomain()
        assertEquals("url", domain.webImage?.url)
        assertEquals(1.5f, domain.webImage?.ratio)
    }

    @Test
    fun `toEntity should map webImage correctly when present`() {
        val domain = ArtObjectLight(
            objectNumber = "A1",
            title = "Art Title",
            hasImage = true,
            webImage = ArtObjectLight.Image(1.5f, "url"),
        )
        val entity = domain.toEntity("query")
        assertEquals("url", entity.imageUrl)
        assertEquals(1.5f, entity.imageRatio)
    }

    @Test
    fun `toEntity should include query`() {
        val domain = ArtObjectLight(
            objectNumber = "A1",
            title = "Art Title",
            hasImage = true,
            webImage = ArtObjectLight.Image(1.5f, "url"),
        )
        val entity = domain.toEntity("query")
        assertEquals("query", entity.query)
    }

    @Test
    fun `toEntity should map null imageUrl and imageRatio when webImage is null`() {
        val domain = ArtObjectLight(
            objectNumber = "A1",
            title = "Art Title",
            hasImage = false,
            webImage = null,
        )
        val entity = domain.toEntity("query")
        assertNull(entity.imageUrl)
        assertNull(entity.imageRatio)
    }
}
