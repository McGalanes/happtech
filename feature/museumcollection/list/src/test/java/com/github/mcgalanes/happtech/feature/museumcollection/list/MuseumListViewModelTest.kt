package com.github.mcgalanes.happtech.feature.museumcollection.list

import app.cash.turbine.test
import com.github.mcgalanes.happtech.core.domain.model.ArtObject
import com.github.mcgalanes.happtech.feature.museumcollection.list.fake.FakeRijksMuseumRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MuseumListViewModelTest {

    @Test
    fun `on init, should load museum collection with France query`() = runTest {
        // GIVEN
        val initialData =
            listOf(
                ArtObject(
                    objectNumber = "1",
                    title = "Title 1",
                    webImage = null,
                    hasImage = false,
                ),
            )

        val repository = FakeRijksMuseumRepository().apply {
            setDataForRefresh(initialData)
            setShouldRefreshSucceed(true)
        }

        // WHEN
        val viewModel = MuseumListViewModel(repository)

        // THEN
        viewModel.uiState.test {
            awaitItem() //skip the initial state
            val actual = awaitItem()

            Assert.assertEquals(
                UiState(
                    query = "France",
                    items = initialData.toImmutableList(),
                ),
                actual,
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on query change, should update the query`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository()
        val viewModel = MuseumListViewModel(repository)
        val query = "Italy"

        // WHEN
        viewModel.onQueryChange(query)

        //THEN
        viewModel.uiState.test {
            awaitItem()
            val actual = awaitItem()
            Assert.assertEquals(query, actual.query)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on search click, should refresh the collection`() = runTest {
        // GIVEN
        val repository = FakeRijksMuseumRepository().apply {
            setShouldRefreshSucceed(true)
        }

        val viewModel = MuseumListViewModel(repository)

        val searchData =
            listOf(
                ArtObject(
                    objectNumber = "2",
                    title = "Title 2",
                    webImage = null,
                    hasImage = false,
                ),
            )

        repository.setDataForRefresh(searchData)

        // WHEN
        viewModel.onSearchClick()

        // THEN
        viewModel.uiState.test {
            awaitItem() //skip the initial state
            val actual = awaitItem()
            Assert.assertEquals(searchData.toImmutableList(), actual.items)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
