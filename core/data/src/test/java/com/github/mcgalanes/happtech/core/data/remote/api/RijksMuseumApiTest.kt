package com.github.mcgalanes.happtech.core.data.remote.api

import com.github.mcgalanes.happtech.core.data.remote.mock.mockEngine
import com.github.mcgalanes.happtech.core.data.remote.payload.FakeRijksMuseumPayloads
import com.github.mcgalanes.happtech.core.network.createRijksMuseumHttpClient
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RijksMuseumApiTest {

    @Test
    fun `getCollection, should return success result`() = runTest {
        // GIVEN
        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getCollectionCorrectPayload()
        )
        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")
        val api = RijksMuseumApi.Default(client = client)

        // WHEN
        val result = api.getCollection()

        // THEN
        assert(result.isSuccess)
    }

    @Test
    fun `getCollection, should return failure result`() = runTest {
        // GIVEN
        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getCollectionWrongPayload()
        )
        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")
        val api = RijksMuseumApi.Default(client = client)

        // WHEN
        val result = api.getCollection()

        // THEN
        assert(result.isFailure)
    }

    @Test
    fun `getCollection, should call correct endpoint`() = runTest {
        // GIVEN
        lateinit var actualUrl: Url

        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getCollectionCorrectPayload(),
            onInterceptUrl = { actualUrl = it },
        )

        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")

        val api = RijksMuseumApi.Default(client = client)

        // WHEN
        api.getCollection()

        // THEN
        assertEquals("/api/en/collection", actualUrl.encodedPath)
    }

    @Test
    fun `getCollection, should call with correct query parameters`() = runTest {
        // GIVEN
        lateinit var actualUrl: Url

        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getCollectionCorrectPayload(),
            onInterceptUrl = { actualUrl = it },
        )

        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")

        val api = RijksMuseumApi.Default(client = client)

        val query = "Leonardo da Vinci"

        // WHEN
        api.getCollection(query = query)

        // THEN
        assertEquals(query, actualUrl.parameters["q"])
    }

    @Test
    fun `getArtObjectDetail, should return success result`() = runTest {
        // GIVEN
        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getArtObjectDetailCorrectPayload()
        )
        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")
        val api = RijksMuseumApi.Default(client = client)

        // WHEN
        val result = api.getArtObjectDetail(objectNumber = "object-12")

        // THEN
        assert(result.isSuccess)
    }

    @Test
    fun `getArtObjectDetail, should return failure result`() = runTest {
        // GIVEN
        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getArtObjectDetailWrongPayload()
        )
        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")
        val api = RijksMuseumApi.Default(client = client)

        // WHEN
        val result = api.getArtObjectDetail(objectNumber = "object-12")

        // THEN
        assert(result.isFailure)
    }

    @Test
    fun `getArtObjectDetail, should call correct endpoint`() = runTest {
        // GIVEN
        lateinit var actualUrl: Url

        val engine = mockEngine(
            statusCode = HttpStatusCode.OK,
            content = FakeRijksMuseumPayloads.getArtObjectDetailCorrectPayload(),
            onInterceptUrl = { actualUrl = it },
        )

        val client = createRijksMuseumHttpClient(engine = engine, apiKey = "1234-abcde")

        val api = RijksMuseumApi.Default(client = client)

        // WHEN
        api.getArtObjectDetail(objectNumber = "object-12")

        // THEN
        assertEquals("/api/en/collection/object-12", actualUrl.encodedPath)
    }
}
