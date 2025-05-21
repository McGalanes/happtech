package com.github.mcgalanes.happtech.feature.museumcollection.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectDetail
import com.github.mcgalanes.happtech.core.navigation.NavScreen.MuseumDetail.Companion.ARG_OBJECT_NUMBER
import com.github.mcgalanes.happtech.feature.museumcollection.fake.FakeRijksMuseumRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MuseumDetailViewModelTest {

    @Test
    fun `on init, should show art object when success`() = runTest {
        // GIVEN
        val artObjectDetail = getFakeArtObjectDetail()
        val repository = FakeRijksMuseumRepository().apply {
            setDataForDetail(artObjectDetail)
        }

        val handle =
            SavedStateHandle(mapOf(ARG_OBJECT_NUMBER to artObjectDetail.objectNumber))

        // WHEN
        val viewModel =
            MuseumDetailViewModel(
                handle = handle,
                repository = repository,
            )

        //THEN
        viewModel.uiState.test {
            val actual = awaitItem()

            Assert.assertEquals(
                UiState(
                    loading = false,
                    artObject = artObjectDetail,
                ),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on init, should show error when failure`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository().apply {
            setDataForDetail(null)
        }

        val handle =
            SavedStateHandle(mapOf(ARG_OBJECT_NUMBER to "object-12"))

        // WHEN
        val viewModel =
            MuseumDetailViewModel(
                handle = handle,
                repository = repository,
            )

        //THEN
        viewModel.uiState.test {
            val actual = awaitItem()

            Assert.assertEquals(
                UiState(
                    loading = false,
                    artObject = null,
                ),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on summary header click, should expand when collapsed`() = runTest {
        // GIVEN
        val artObjectDetail = getFakeArtObjectDetail()
        val repository = FakeRijksMuseumRepository().apply {
            setDataForDetail(artObjectDetail)
        }

        val handle =
            SavedStateHandle(mapOf(ARG_OBJECT_NUMBER to artObjectDetail.objectNumber))

        val viewModel =
            MuseumDetailViewModel(
                handle = handle,
                repository = repository,
            )

        //WHEN
        viewModel.onSummaryHeaderClick()

        //THEN
        viewModel.uiState.test {
            val actual = awaitItem()

            Assert.assertTrue(actual.expanded)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on summary header click, should collapse when expanded`() = runTest {
        // GIVEN
        val artObjectDetail = getFakeArtObjectDetail()
        val repository = FakeRijksMuseumRepository().apply {
            setDataForDetail(artObjectDetail)
        }

        val handle =
            SavedStateHandle(mapOf(ARG_OBJECT_NUMBER to artObjectDetail.objectNumber))

        val viewModel =
            MuseumDetailViewModel(
                handle = handle,
                repository = repository,
            )

        viewModel.onSummaryHeaderClick() //expand

        //WHEN
        viewModel.onSummaryHeaderClick()

        //THEN
        viewModel.uiState.test {
            val actual = awaitItem()

            Assert.assertFalse(actual.expanded)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on retry, should show art object when success`() = runTest {
        // GIVEN
        val artObjectDetail = getFakeArtObjectDetail()
        val repository = FakeRijksMuseumRepository().apply {
            setDataForDetail(null)
        }

        val handle =
            SavedStateHandle(mapOf(ARG_OBJECT_NUMBER to artObjectDetail.objectNumber))

        val viewModel =
            MuseumDetailViewModel(
                handle = handle,
                repository = repository,
            )

        repository.setDataForDetail(artObjectDetail)

        //WHEN
        viewModel.onRetryClick()

        //THEN
        viewModel.uiState.test {
            val actual = awaitItem()

            Assert.assertEquals(
                UiState(
                    loading = false,
                    artObject = artObjectDetail,
                ),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}

private fun getFakeArtObjectDetail(): ArtObjectDetail {
    return ArtObjectDetail(
        objectNumber = "object-12",
        title = "The Night Watch",
        description = "A famous painting by Rembrandt.",
        objectCollection = listOf("Rijksmuseum", "Amsterdam"),
        techniques = listOf("Oil on canvas"),
        principalMakers = listOf(
            ArtObjectDetail.PrincipalMaker(
                name = "Rembrandt van Rijn",
                dateOfBirth = "1606",
                dateOfDeath = "1669",
            ),
        ),
        presentingDate = "1642",
        documentation = listOf("https://www.rijksmuseum.nl/en/collection/SK-C-5"),
        dimensions = listOf(
            ArtObjectDetail.Dimension(
                unit = "cm",
                type = "Height",
                value = "363",
            ),
            ArtObjectDetail.Dimension(
                unit = "cm",
                type = "Width",
                value = "437",
            ),
        ),
        image = ArtObjectDetail.Image(
            ratio = 1.5f,
            url = "https://www.rijksmuseum.nl/en/collection/SK-C-5/image",
        ),
    )
}
