package com.noor.compose.ui.brochure.remote

import com.noor.compose.remote.RemoteProviders
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BrochureRepositoryTest {

    private lateinit var remoteProviders: RemoteProviders
    private lateinit var repository: BrochureRepository

    @Before
    fun setup() {
        remoteProviders = mockk()
        repository = BrochureRepository(remoteProviders)
    }

    @Test
    fun `getBrochures returns only brochures within 5km range and valid contentTypes`() = runTest {
        // GIVEN
        val mockResponse = BrochureResponse(
            _embedded = BrochureResponse.Embedded(
                contents = listOf(
                    BrochureResponse.EmbeddedContent(
                        contentType = "brochure",
                        content = listOf(
                            BrochureResponse.BrochureContent(
                                contentId = "1",
                                brochureImage = "image1.jpg",
                                distance = 4.0,
                                publisher = BrochureResponse.BrochurePublisher(name = "Retailer A")
                            ),
                            BrochureResponse.BrochureContent(
                                contentId = "2",
                                brochureImage = "image2.jpg",
                                distance = 10.0,
                                publisher = BrochureResponse.BrochurePublisher(name = "Retailer B")
                            )
                        )
                    ),
                    BrochureResponse.EmbeddedContent(
                        contentType = "irrelevantType",
                        content = listOf(
                            BrochureResponse.BrochureContent(
                                contentId = "3",
                                brochureImage = null,
                                distance = 2.0,
                                publisher = BrochureResponse.BrochurePublisher(name = "Retailer C")
                            )
                        )
                    )
                )
            )
        )

        coEvery { remoteProviders.brochureApi.getBrochures() } returns mockResponse

        // WHEN
        val result = repository.getBrochures()

        // THEN
        assertEquals(1, result.size)
        assertEquals("1", result.first().id)
        assertEquals("Retailer A", result.first().retailerName)
    }
}