package com.noor.compose.ui.brochure

import com.noor.compose.ui.brochure.model.BrochureItem
import com.noor.compose.ui.brochure.remote.BrochureRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BrochureListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: BrochureRepository
    private lateinit var viewModel: BrochureListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // control Dispatchers.Main manually
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loads brochures successfully and updates state`() = runTest {
        // Given
        val mockData = listOf(
            BrochureItem("1", "brochure", "Retailer 1", "image.jpg", 2.0)
        )
        coEvery { repository.getBrochures() } returns mockData

        // When
        viewModel = BrochureListViewModel(repository)

        // Let coroutine run
        advanceUntilIdle()

        // Then
        val state = viewModel.brochureState.value
        assertFalse(state.isLoading)
        assertEquals(mockData, state.brochures)
        assertNull(state.error)
    }

    @Test
    fun `handles error when repository throws exception`() = runTest {
        // Given
        coEvery { repository.getBrochures() } throws RuntimeException("Network failed")

        // When
        viewModel = BrochureListViewModel(repository)

        // Let coroutine run
        advanceUntilIdle()

        // Then
        val state = viewModel.brochureState.value
        assertFalse(state.isLoading)
        assertEquals("Network failed", state.error)
        assertTrue(state.brochures.isEmpty())
    }
}