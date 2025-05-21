package com.github.mcgalanes.happtech.feature.museumcollection.list

import app.cash.turbine.test
import com.github.mcgalanes.happtech.core.domain.model.ArtObjectLight
import com.github.mcgalanes.happtech.feature.museumcollection.list.fake.FakeRijksMuseumRepository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MuseumListViewModelTest {

    @Test
    fun `init should show data`() = runTest {
        // GIVEN
        val artObjectLightList = createFakeArtObjectLightList()

        val repository = FakeRijksMuseumRepository().apply {
            mockSetCollectionData(data = artObjectLightList)
            mockRefreshCollectionSucceed(succeed = true)
        }

        // WHEN
        val viewModel = MuseumListViewModel(repository)

        // THEN
        viewModel.uiState.test {
            awaitItem() //skip initial state
            val actual = awaitItem()
            Assert.assertEquals(
                UiState(
                    query = "",
                    items = artObjectLightList,
                ),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `init should show error if refresh fail`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository().apply {
            mockSetCollectionData(data = emptyList())
            mockRefreshCollectionSucceed(succeed = false)
        }

        // WHEN
        val viewModel = MuseumListViewModel(repository)

        // THEN
        viewModel.uiEvents.test {
            val actual = awaitItem()
            Assert.assertEquals(
                UiEvent.ShowSnackBar(R.string.feature_museumcollection_error_default),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on query change should update query`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository()
        val viewModel = MuseumListViewModel(repository)

        val query = "new query"

        // WHEN
        viewModel.onQueryChange(query)

        // THEN
        viewModel.uiState.test {
            awaitItem() //skip initial state
            val actual = awaitItem()
            Assert.assertEquals(query, actual.query)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on search click should show fresh data if succeed`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository().apply {
            mockSetCollectionData(data = emptyList())
            mockRefreshCollectionSucceed(succeed = true)
        }

        val viewModel = MuseumListViewModel(repository)

        viewModel.onQueryChange(query = "new search")

        val freshArtObjectLightList = createFakeArtObjectLightList()
        repository.apply {
            mockSetCollectionData(freshArtObjectLightList)
            mockRefreshCollectionSucceed(true)
        }

        // WHEN
        viewModel.onSearchClick()

        // THEN
        viewModel.uiState.test {
            awaitItem() //skip initial state
            val actual = awaitItem()
            Assert.assertEquals(
                freshArtObjectLightList,
                actual.items,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on search click should show error if refresh fail`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository().apply {
            mockSetCollectionData(data = emptyList())
            mockRefreshCollectionSucceed(succeed = true)
        }

        val viewModel = MuseumListViewModel(repository)

        viewModel.onQueryChange(query = "new search")

        repository.apply {
            mockSetCollectionData(data = emptyList())
            mockRefreshCollectionSucceed(succeed = false)
        }

        // WHEN
        viewModel.onSearchClick()

        // THEN
        viewModel.uiEvents.test {
            val actual = awaitItem()
            Assert.assertEquals(
                UiEvent.ShowSnackBar(R.string.feature_museumcollection_error_default),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun createFakeArtObjectLightList() =
        persistentListOf(
            ArtObjectLight(
                objectNumber = "A1",
                title = "Art Title",
                hasImage = true,
                webImage = ArtObjectLight.Image(
                    ratio = 1.5f,
                    url = "url",
                ),
            ),
            ArtObjectLight(
                objectNumber = "A2",
                title = "Art Title 2",
                hasImage = true,
                webImage = ArtObjectLight.Image(
                    ratio = 1.7f,
                    url = "url 2",
                ),
            ),
        )
}
