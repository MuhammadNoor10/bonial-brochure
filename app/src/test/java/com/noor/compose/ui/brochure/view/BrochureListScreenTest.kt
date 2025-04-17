package com.noor.compose.ui.brochure.view

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.noor.compose.ui.brochure.model.BrochureItem
import com.noor.compose.ui.brochure.state.BrochureState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BrochureListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testItems = listOf(
        BrochureItem(
            id = "1",
            contentType = "brochure",
            retailerName = "Retailer One",
            brochureImage = "some-image.png",
            distance = 1.1
        ),
        BrochureItem(
            id = "2",
            contentType = "brochurePremium",
            retailerName = "Retailer Two",
            brochureImage = "some-image2.png",
            distance = 2.2
        )
    )

    @Test
    fun showsLoadingIndicator_whenStateIsLoading() {
        composeTestRule.setContent {
            BuildBrochureListScreen(
                state = BrochureState(isLoading = true),
                onEvent = {}
            )
        }

        composeTestRule.onNode(isSelectable()).assertExists()
    }

    @Test
    fun showsErrorText_whenErrorOccurs() {
        val errorMessage = "Ops.. Something went wrong"
        composeTestRule.setContent {
            BuildBrochureListScreen(
                state = BrochureState(error = errorMessage),
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Error: $errorMessage").assertIsDisplayed()
    }

    @Test
    fun rendersBrochureCards_whenDataAvailable() {
        composeTestRule.setContent {
            BuildBrochureListScreen(
                state = BrochureState(brochures = testItems),
                onEvent = {}
            )
        }

        composeTestRule.onAllNodesWithText("Retailer One").assertCountEquals(1)
        composeTestRule.onAllNodesWithText("Retailer Two").assertCountEquals(1)
    }

    @Test
    fun rendersEmptyText_whenListIsEmpty() {
        composeTestRule.setContent {
            BuildBrochureListScreen(
                state = BrochureState(brochures = emptyList()),
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("No brochures available").assertIsDisplayed()
    }
}